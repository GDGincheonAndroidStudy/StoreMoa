/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seunghyo.storemoa;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SuperAwesomeCardFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private PagerAdapter adapter2;
    private int position;
    private boolean DEBUG = true;
    private String TAG = "SUperAwesomeCardFragment";
    String query;
    Data data = new Data();

    public static SuperAwesomeCardFragment newInstance(int position) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.getInstance().printLog(DEBUG, TAG, String.valueOf(data.product_name.size()));
        if(data.product_name.size() == 0) {
            new MyAsyncTask().execute();
        }
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView;
        rootView = inflater.inflate(R.layout.main, container, false);
        rootView.setBackgroundColor(Color.WHITE);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        CustomList customList = new CustomList(getActivity());
        listView.setAdapter(customList);
        return rootView;
    }

    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;
        public CustomList(Activity context ) {
            super(context, R.layout.listitem, data.product_name);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView price = (TextView) rowView.findViewById(R.id.price);
            title.setText(data.product_name.get(position));
            imageView.setImageResource(R.mipmap.ic_launcher);
            price.setText(data.product_price.get(position));
            return rowView;
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