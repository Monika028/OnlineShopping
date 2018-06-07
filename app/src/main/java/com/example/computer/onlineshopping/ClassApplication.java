package com.example.computer.onlineshopping;
/**
 * Created by Computer on 3/21/2018.
 */

import android.app.Application;
import com.onesignal.OneSignal;
import com.backendless.Backendless;
import com.razorpay.Checkout;

import io.realm.Realm;

public class ClassApplication extends Application {

    public static final String APPLICATION_ID = "F0CF1527-885B-4D70-FF8B-F392A6FFE100";
    public static final String API_KEY = "8F4D28E7-C783-0D4C-FF72-9BD69B964100";


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        Checkout.preload(getApplicationContext());


        Backendless.initApp(this,APPLICATION_ID,API_KEY);
    }
}