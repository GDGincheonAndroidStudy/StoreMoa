package com.seunghyo.storemoa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SplashActivity extends Activity {

    private boolean DEBUG = true;
    private String TAG = "SUperAwesomeCardFragment";
    String query;
    Data data = new Data();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            new MyAsyncTask().execute();
            Thread.sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            query = "select * from cu_price";
            ArrayList<String> list = new ArrayList<String>();

            ResultSet reset = null;
            Connection conn = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://117.16.244.162;databaseName=201101633", "201101633", "as871100");
                Statement stmt = conn.createStatement();

                reset = stmt.executeQuery(query);
                int i=0;
                while (reset.next()) {
                    if (isCancelled()) break;
                    data.product_name.add(i, reset.getString(1));
                    data.product_price.add(i, reset.getString(2));
                    Util.getInstance().printLog(DEBUG, TAG, "number is: " + i + " String is: " + data.product_name.get(i));
                    i++;
                }
                conn.close();
            } catch (Exception e)
            {
                Log.w("Error connection", "" + e.getMessage());
            }

            return list;

        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
