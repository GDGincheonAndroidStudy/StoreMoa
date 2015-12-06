package com.seunghyo.storemoa;

import java.util.ArrayList;

/**
 * Created by SeungHyo on 2015-12-06.
 */
public class Data {
    private static Data m_instance;

    public static Data getInstance() {
        if (m_instance == null) {
            m_instance = new Data();
        }
        return m_instance;
    }

    private ArrayList<String> product_name = new ArrayList<>();
    private ArrayList<String> product_price= new ArrayList<>();

    public ArrayList<String> getProduct_name() {
        return product_name;
    }

    public void setProduct_name(ArrayList<String> product_name) {
        this.product_name = product_name;
    }

    public ArrayList<String> getProduct_price() {
        return product_price;
    }

    public void setProduct_price(ArrayList<String> product_price) {
        this.product_price = product_price;
    }
}
