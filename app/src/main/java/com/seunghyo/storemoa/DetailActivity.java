package com.seunghyo.storemoa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by SeungHyo on 2015-12-07.
 */
public class DetailActivity extends Activity {

    Data data = new Data();
    int position;
    String name_str = Data.getInstance().getProduct_name().get(position);
    String positions;
    ArrayAdapter<String> adapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView product_name = (TextView) findViewById(R.id.pro_name);
        TextView manufac = (TextView) findViewById(R.id.manufac);
        TextView cu_price = (TextView) findViewById(R.id.cu_price);
        TextView seven_price = (TextView) findViewById(R.id.seven_price);
        TextView gs_price = (TextView) findViewById(R.id.gs_price);
        TextView cu_past_price = (TextView) findViewById(R.id.cu_past_price);
        TextView seven_past_price = (TextView) findViewById(R.id.seven_past_prcie);
        TextView gs_past_prcie = (TextView) findViewById(R.id.gs_past_prcie);
        TextView cu_date = (TextView) findViewById(R.id.cu_date);
        TextView gs_date = (TextView) findViewById(R.id.gs_gate);
        TextView seven_date = (TextView) findViewById(R.id.seven_date);
        TextView sale = (TextView) findViewById(R.id.sale);
        final EditText input_grade = (EditText) findViewById(R.id.inputgrade);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        Button btn_ok = (Button) findViewById(R.id.input);
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        TextView seven1 = (TextView) findViewById(R.id.seven1);
        TextView seven2 = (TextView) findViewById(R.id.seven2);
        TextView gs1 = (TextView) findViewById(R.id.gs1);
        TextView gs2 = (TextView) findViewById(R.id.gs2);
        TextView cu1 = (TextView) findViewById(R.id.cu1);
        TextView cu2 = (TextView) findViewById(R.id.cu2);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Myasycktask().execute();
            }
        });

        Intent intent = getIntent();

        position = intent.getIntExtra("position", 0);
        positions = intent.getStringExtra("positions");

        name_str = Data.getInstance().getProduct_name().get(position);

        if(positions.equals("seven_price")) {
            seven1.setTextColor(Color.BLUE);
            seven2.setTextColor(Color.BLUE);
            seven_price.setTextColor(Color.BLUE);
            seven_past_price.setTextColor(Color.BLUE);
            seven_date.setTextColor(Color.BLUE);
        } else if(positions.equals("cu_price")) {
            cu1.setTextColor(Color.BLUE);
            cu2.setTextColor(Color.BLUE);
            cu_price.setTextColor(Color.BLUE);
            cu_past_price.setTextColor(Color.BLUE);
            cu_date.setTextColor(Color.BLUE);
        } else {
            gs1.setTextColor(Color.BLUE);
            gs2.setTextColor(Color.BLUE);
            gs_price.setTextColor(Color.BLUE);
            gs_past_prcie.setTextColor(Color.BLUE);
            gs_date.setTextColor(Color.BLUE);
        }

        product_name.setTextSize(20);
        product_name.setText(Data.getInstance().getProduct_name().get(position));
        manufac.setTextSize(13);
        manufac.setText(Data.getInstance().getManufac().get(position));
        cu_price.setText(Data.getInstance().getCu_price().get(position));
        seven_price.setText(Data.getInstance().getSeven_price().get(position));
        gs_price.setText(Data.getInstance().getGs_price().get(position));
        cu_past_price.setText(Data.getInstance().getPast_cu_price().get(position));
        seven_past_price.setText(Data.getInstance().getPast_seven_price().get(position));
        gs_past_prcie.setText(Data.getInstance().getPast_gs_price().get(position));
        cu_date.setText(Data.getInstance().getPast_cu_change_date().get(position));
        gs_date.setText(Data.getInstance().getPast_gs_change_date().get(position));
        seven_date.setText(Data.getInstance().getPast_seven_change_date().get(position));


        if(name_str.equals("코카콜라")){
            imageView.setImageResource(R.drawable.coca);
        }
        else if(name_str.equals("레쓰비 마일드")){
            imageView.setImageResource(R.drawable.letsbe);
        }
        else if(name_str.equals("칠성 사이다")){
            imageView.setImageResource(R.drawable.sida);
        }
        else if(name_str.equals("비타500")){
            imageView.setImageResource(R.drawable.bita);
        }
        else if(name_str.equals("허니버터칩")){
            imageView.setImageResource(R.drawable.huny);
        }
        else if(name_str.equals("아몬드 빼빼로")){
            imageView.setImageResource(R.drawable.amond);
        }
        else if(name_str.equals("초코파이")){
            imageView.setImageResource(R.drawable.choko);
        }
        else if(name_str.equals("홈런볼")){
            imageView.setImageResource(R.drawable.homerun);
        }
        else if(name_str.equals("바나나 우유")){
            imageView.setImageResource(R.drawable.banana);
        }
        else if(name_str.equals("초코 우유")){
            imageView.setImageResource(R.drawable.chokomilk);
        }
        else if(name_str.equals("딸기 우유")){
            imageView.setImageResource(R.drawable.ddalgi);
        }
        else if(name_str.equals("삼각 김밥")){
            imageView.setImageResource(R.drawable.samgak);
        }
        else if(name_str.equals("즉석 도시락")){
            imageView.setImageResource(R.drawable.dosirak);
        }
        else if(name_str.equals("육개장 큰컵")){
            imageView.setImageResource(R.drawable.ukgae);
        }
        else if(name_str.equals("진짜장 큰컵")){
            imageView.setImageResource(R.drawable.jinjja);
        }
        else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }

        try {
            String saletest = Data.getInstance().getSale_content().get(position);
            if(saletest.equals(null)) {
                sale.setText("행사 내용 없음");
            } else {
                sale.setTextColor(Color.RED);
                sale.setText("행사내용 : " + Data.getInstance().getSale_content().get(position));
            }
        } catch (NullPointerException e) {
            sale.setText("행사 내용 없음");
        }
        new Myasycktask2().execute();
    }

    class Myasycktask extends AsyncTask {
        EditText input_grade = (EditText) findViewById(R.id.inputgrade);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        String query;
        String product_name = Data.getInstance().getProduct_name().get(position);
        String grade_content = input_grade.getText().toString();
        float grade = ratingBar.getRating();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Object doInBackground(Object[] params) {

            query = "insert into rating values('"+product_name+"',"+ grade +",'"+grade_content+"');";

            Connection conn = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://117.16.244.162;databaseName=201101633", "201101633", "as871100");
                Log.e("success","connection open");
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                stmt.close();
                conn.close();

            } catch (Exception e)
            {
                Log.e("Error connection", "" + e.getMessage());
            }
            return null;
        }
    }

    class Myasycktask2 extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            Log.e("proname",name_str);
            String query = "select grade, grade_content from rating where product_name = '" + name_str+ "'";
            ArrayList<String> list = new ArrayList<String>();
            ResultSet reset = null;
            Connection conn = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://117.16.244.162;databaseName=201101633", "201101633", "as871100");
                Statement stmt = conn.createStatement();

                reset = stmt.executeQuery(query);

                while (reset.next()) {
                    if (isCancelled()) break;
                    final String str ="평점 : " + reset.getString(1) + "점   평가내용 : "+reset.getString(2);
                    list.add(str);
                }
                reset.close();
                stmt.close();
                conn.close();
            } catch (Exception e)
            {
                Log.w("Error connection", "" + e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            adapter.clear();
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
