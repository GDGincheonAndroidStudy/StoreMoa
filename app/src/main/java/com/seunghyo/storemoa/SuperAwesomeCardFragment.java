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
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SuperAwesomeCardFragment extends Fragment {

	private static final String ARG_POSITION = "position";
    private PagerAdapter adapter2;
	private int position;
    ArrayAdapter<String> adapter;
    MyAsyncTask mTask;
    String query;

    private List<String> product_name = new List<String>() {
        @Override
        public void add(int location, String object) {

        }

        @Override
        public boolean add(String object) {
            return false;
        }

        @Override
        public boolean addAll(int location, Collection<? extends String> collection) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends String> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean contains(Object object) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public String get(int location) {
            return null;
        }

        @Override
        public int indexOf(Object object) {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @NonNull
        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @Override
        public int lastIndexOf(Object object) {
            return 0;
        }

        @Override
        public ListIterator<String> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<String> listIterator(int location) {
            return null;
        }

        @Override
        public String remove(int location) {
            return null;
        }

        @Override
        public boolean remove(Object object) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public String set(int location, String object) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public List<String> subList(int start, int end) {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(T[] array) {
            return null;
        }
    };
    private List<String> product_price = new List<String>() {
        @Override
        public void add(int location, String object) {

        }

        @Override
        public boolean add(String object) {
            return false;
        }

        @Override
        public boolean addAll(int location, Collection<? extends String> collection) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends String> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean contains(Object object) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public String get(int location) {
            return null;
        }

        @Override
        public int indexOf(Object object) {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @NonNull
        @Override
        public Iterator<String> iterator() {
            return null;
        }

        @Override
        public int lastIndexOf(Object object) {
            return 0;
        }

        @Override
        public ListIterator<String> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<String> listIterator(int location) {
            return null;
        }

        @Override
        public String remove(int location) {
            return null;
        }

        @Override
        public boolean remove(Object object) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public String set(int location, String object) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public List<String> subList(int start, int end) {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(T[] array) {
            return null;
        }
    };

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
        handler.sendEmptyMessage(0);
		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

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
            super(context, R.layout.listitem, product_name);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView price = (TextView) rowView.findViewById(R.id.price);
            title.setText(product_name.get(position));
            imageView.setImageResource(R.mipmap.ic_launcher);
            price.setText(product_price.get(position));
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

                while (reset.next()) {
                    if (isCancelled()) break;
                    product_name.add(reset.getString(1));
                    product_price.add(reset.getString(2));
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
            adapter.clear();
            adapter.addAll(list);

            adapter.notifyDataSetChanged();
            handler.sendEmptyMessageDelayed(0, 1000);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mTask = new MyAsyncTask();

            mTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        }
    };
}