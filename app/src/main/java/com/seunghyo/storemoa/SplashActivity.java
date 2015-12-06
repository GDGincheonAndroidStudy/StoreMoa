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

            query = "select * from total";
            ArrayList<String> list = new ArrayList<String>();

            ArrayList<String> product_name = new ArrayList<>();
            ArrayList<String> product_type = new ArrayList<>();
            ArrayList<String> manufac = new ArrayList<>();
            ArrayList<String> cu_price = new ArrayList<>();
            ArrayList<String> gs_price = new ArrayList<>();
            ArrayList<String> seven_price = new ArrayList<>();
            ArrayList<String> past_cu_price = new ArrayList<>();
            ArrayList<String> past_gs_price = new ArrayList<>();
            ArrayList<String> past_seven_price = new ArrayList<>();
            ArrayList<String> past_cu_change_date = new ArrayList<>();
            ArrayList<String> past_gs_change_date = new ArrayList<>();
            ArrayList<String> past_seven_change_date = new ArrayList<>();
            ArrayList<String> sale_check = new ArrayList<>();
            ArrayList<String> sale_content = new ArrayList<>();
            ArrayList<String> grade = new ArrayList<>();
            ArrayList<String> grade_content = new ArrayList<>();

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
                    product_name.add(i, reset.getString(1));
                    product_type.add(i, reset.getString(2));
                    manufac.add(i, reset.getString(3));
                    cu_price.add(i, reset.getString(4));
                    gs_price.add(i, reset.getString(5));
                    seven_price.add(i, reset.getString(6));
                    past_cu_price.add(i, reset.getString(7));
                    past_gs_price.add(i, reset.getString(8));
                    past_seven_price.add(i, reset.getString(9));
                    past_cu_change_date.add(i, reset.getString(10));
                    past_gs_change_date.add(i, reset.getString(11));
                    past_seven_change_date.add(i, reset.getString(12));
                    sale_content.add(i, reset.getString(13));
                    Util.getInstance()
                            .printLog(DEBUG, TAG, "number is: " + i + " String is: " + product_name.get(i));
                    i++;
                }

                /**
                 * 요부분에서 데이타에 밀어 넣는 겁니다.
                 * 인스턴스를 들고 있어야 값이 사라지지 않아요
                 */

                if(product_name.size()>0){
                    Data.getInstance().setProduct_name(product_name);
                    Data.getInstance().setProduct_type(product_type);
                    Data.getInstance().setManufac(manufac);
                    Data.getInstance().setCu_price(cu_price);
                    Data.getInstance().setGs_price(gs_price);
                    Data.getInstance().setSeven_price(seven_price);
                    Data.getInstance().setPast_cu_price(past_cu_price);
                    Data.getInstance().setPast_gs_price(past_gs_price);
                    Data.getInstance().setPast_seven_price(past_seven_price);
                    Data.getInstance().setPast_cu_change_date(past_cu_change_date);
                    Data.getInstance().setPast_gs_change_date(past_gs_change_date);
                    Data.getInstance().setPast_seven_change_date(past_seven_change_date);
                    Data.getInstance().setSale_content(sale_content);
                }
                reset.close();
                stmt.close();
                conn.close();
                Log.w("Success connection", "connclosed");
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
