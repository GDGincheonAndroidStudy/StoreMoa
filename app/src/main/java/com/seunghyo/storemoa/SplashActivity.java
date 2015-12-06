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
            Thread.sleep(4000);

            new MyAsyncTask().execute();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
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

            ArrayList<String> name =  new ArrayList<>();
            ArrayList<String> price =  new ArrayList<>();

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
                    name.add(i, reset.getString(1));
                    price.add(i, reset.getString(2));
                    Util.getInstance()
                            .printLog(DEBUG, TAG, "number is: " + i + " String is: " + name.get(i));
                    i++;
                }

                /**
                 * 요부분에서 데이타에 밀어 넣는 겁니다.
                 * 인스턴스를 들고 있어야 값이 사라지지 않아요
                 */

                if(name.size()>0){
                    Data.getInstance().setProduct_name(name);
                    Data.getInstance().setProduct_price(price);
                }

                conn.close();

            } catch (Exception e)
            {
                Log.w("Error connection", "" + e.getMessage());
            }

            return list;

        }

        /**
         * 여기가 어싱크가 끝나는 부분 입니다 어싱크는 비동기라 둬놓고 스레드 걸면 가끔 헛돌아요
         *
         * 어싱크가 끝난다 -> 파싱 & 모든 스플래시에서 받는 데이타들이 정상으로 들어 온다.
         *
         * 그러면 다음 메인 엑티비티를 실행해라 라는 구문이 더 좋습니다
         * @param list
         */
        @Override
        protected void onPostExecute(ArrayList<String> list) {

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
