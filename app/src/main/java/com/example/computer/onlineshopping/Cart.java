package com.example.computer.onlineshopping;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Cart extends RealmObject {
    public String product_Name;
    public int quantity;
    public long productPrice;

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }




}
