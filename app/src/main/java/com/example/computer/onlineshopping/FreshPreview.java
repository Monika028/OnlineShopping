package com.example.computer.onlineshopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmResults;

public class FreshPreview extends AppCompatActivity implements View.OnClickListener,PaymentResultListener {
    TextView textView,tv1,tv2;
    NetworkImageView nv;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ImageLoader imageLoader;
    RequestQueue requestQueue;
    LayoutInflater inflater;

    Button  btn_1, btn_2, btn_3, btn_4, btn_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_preview);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.tv_title);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        nv = (NetworkImageView) findViewById(R.id.nv_icon);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        requestQueue = Volley.newRequestQueue(FreshPreview.this);


        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mcache = new LruCache<>(10);

            @Override
            public Bitmap getBitmap(String url) {
                return mcache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mcache.put(url, bitmap);

            }

        });

        String string = getIntent().getExtras().getString("hello");
        try {
            jsonObject = new JSONObject(string);
            textView.setText(jsonObject.getString("bigprice"));
            tv1.setText(jsonObject.getString("Details"));
            nv.setImageUrl(jsonObject.getString("imagebig"), imageLoader);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {

        Button btn = (Button) view;
        if (view.getId() == R.id.btn_1) {
            //call to payment method
            payment();


        } else if (view.getId() == R.id.btn_2) {

//            Intent intent = new Intent(FreshPreview.this, null);


            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Cart cart = realm.createObject(Cart.class);
                    cart.setProduct_Name("Nirma");
                    cart.setProductPrice(10);
                    cart.setQuantity(20);

                }
            });



            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Cart> results = realm.where(Cart.class).findAll();
                    for (int i=0;i<results.size();i++) {
                        Cart c = results.get(i);
                        if (c.getProduct_Name().equalsIgnoreCase("Nirma")) {
                            c.setQuantity(3);
                        }
                    }
                }
            });


            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    RealmResults<Cart> res = realm.where(Cart.class).equalTo("productPrice",100).findAll();

                }
            });


        } else if (view.getId() == R.id.btn_3) {

            Intent intent = new Intent(FreshPreview.this,Shipping.class);
            startActivity(intent);


        } else if (view.getId() == R.id.btn_4) {

            Intent intent = new Intent(FreshPreview.this,ContactUs.class);
            startActivity(intent);


        } else if (view.getId() == R.id.btn_5) {

            Intent intent = new Intent(FreshPreview.this,Payment.class);
            startActivity(intent);

        }else
        {
            Log.d(" Freshcollection","no image is selected");;
        }

    }


    //PAYMENT
    public void payment()
    {
        Checkout checkout = new Checkout();
        checkout.setImage(R.mipmap.myimage);

        final Activity activity = this;

        try

        {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: Rentomojo || HasGeek etc.
             */

            options.put("name", "Online Shopping");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */

            options.put("Detail", "613A2269-7C0E-FCB4-FF0F-5C0AC9263800");
            options.put("Detail", "04B7A5AC-4AD4-EAE7-FF7E-E88582269");
            options.put("Detail", "B5BCB153-3864-9855-FFFC-257E444976");


            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", "150000");
            options.put("amount", "160000");
            options.put("amount", "170000");

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("test", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess (String s){

        Toast.makeText(FreshPreview.this,"Success "+s,Toast.LENGTH_LONG).show();
    }



    @Override
    public void onPaymentError ( int i, String s){

        Toast.makeText(FreshPreview.this,"Error Code "+i+"\nMessage "+s,Toast.LENGTH_LONG).show();

    }

}



