package com.seunghyo.storemoa;

import java.util.ArrayList;

/**
 * Created by SeungHyo on 2015-12-06.
 */
public class Data {
    private static Data m_instance;

    /*

    인스턴스! 인스턴스! 인스턴스!

     */
    public static Data getInstance() {
        if (m_instance == null) {
            m_instance = new Data();
        }
        return m_instance;
    }

    private ArrayList<String> product_name = new ArrayList<String>();
    private ArrayList<String> product_type = new ArrayList<String>();
    private ArrayList<String> manufac = new ArrayList<String>();
    private ArrayList<String> cu_price = new ArrayList<String>();
    private ArrayList<String> gs_price = new ArrayList<String>();
    private ArrayList<String> seven_price = new ArrayList<String>();
    private ArrayList<String> past_cu_price = new ArrayList<String>();
    private ArrayList<String> past_gs_price = new ArrayList<String>();
    private ArrayList<String> past_seven_price = new ArrayList<String>();
    private ArrayList<String> past_cu_change_date = new ArrayList<String>();
    private ArrayList<String> past_gs_change_date = new ArrayList<String>();
    private ArrayList<String> past_seven_change_date = new ArrayList<String>();
    private ArrayList<String> sale_content = new ArrayList<String>();


    public ArrayList<String> getCu_price() {
        return cu_price;
    }

    public void setCu_price(ArrayList<String> cu_price) {
        this.cu_price = cu_price;
    }

    public ArrayList<String> getGs_price() {
        return gs_price;
    }

    public void setGs_price(ArrayList<String> gs_price) {
        this.gs_price = gs_price;
    }

    public static Data getM_instance() {
        return m_instance;
    }

    public static void setM_instance(Data m_instance) {
        Data.m_instance = m_instance;
    }

    public ArrayList<String> getManufac() {
        return manufac;
    }

    public void setManufac(ArrayList<String> manufac) {
        this.manufac = manufac;
    }

    public ArrayList<String> getPast_cu_change_date() {
        return past_cu_change_date;
    }

    public void setPast_cu_change_date(ArrayList<String> past_cu_change_date) {
        this.past_cu_change_date = past_cu_change_date;
    }

    public ArrayList<String> getPast_cu_price() {
        return past_cu_price;
    }

    public void setPast_cu_price(ArrayList<String> past_cu_price) {
        this.past_cu_price = past_cu_price;
    }

    public ArrayList<String> getPast_gs_change_date() {
        return past_gs_change_date;
    }

    public void setPast_gs_change_date(ArrayList<String> past_gs_change_date) {
        this.past_gs_change_date = past_gs_change_date;
    }

    public ArrayList<String> getPast_gs_price() {
        return past_gs_price;
    }

    public void setPast_gs_price(ArrayList<String> past_gs_price) {
        this.past_gs_price = past_gs_price;
    }

    public ArrayList<String> getPast_seven_change_date() {
        return past_seven_change_date;
    }

    public void setPast_seven_change_date(ArrayList<String> past_seven_change_date) {
        this.past_seven_change_date = past_seven_change_date;
    }

    public ArrayList<String> getPast_seven_price() {
        return past_seven_price;
    }

    public void setPast_seven_price(ArrayList<String> past_seven_price) {
        this.past_seven_price = past_seven_price;
    }

    public ArrayList<String> getProduct_type() {
        return product_type;
    }

    public void setProduct_type(ArrayList<String> product_type) {
        this.product_type = product_type;
    }

    public ArrayList<String> getSale_content() {
        return sale_content;
    }

    public void setSale_content(ArrayList<String> sale_content) {
        this.sale_content = sale_content;
    }

    public ArrayList<String> getSeven_price() {
        return seven_price;
    }

    public void setSeven_price(ArrayList<String> seven_price) {
        this.seven_price = seven_price;
    }

    public ArrayList<String> getProduct_name() {
        return product_name;
    }

    public void setProduct_name(ArrayList<String> product_name) {
        this.product_name = product_name;
    }


}
