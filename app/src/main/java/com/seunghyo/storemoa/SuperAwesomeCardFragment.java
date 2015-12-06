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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SuperAwesomeCardFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;
    private boolean DEBUG = true;
    private String TAG = "SUperAwesomeCardFragment";
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
        if(Data.getInstance().getProduct_price().size() >0) {
            Util.getInstance().printLog(DEBUG, TAG, String.valueOf(Data.getInstance().getProduct_name()));
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
            super(context, R.layout.listitem, Data.getInstance().getProduct_name());
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView price = (TextView) rowView.findViewById(R.id.price);
            String name_str = Data.getInstance().getProduct_name().get(position);
            title.setText(name_str);
            String price_str = Data.getInstance().getProduct_price().get(position);
            imageView.setImageResource(R.mipmap.ic_launcher);
            price.setText(price_str);
            return rowView;
        }
    }

}