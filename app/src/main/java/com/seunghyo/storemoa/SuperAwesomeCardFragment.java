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
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SuperAwesomeCardFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int positions;
    private boolean DEBUG = true;
    private String TAG = "SUperAwesomeCardFragment";
    Data data = new Data();
    private PagerAdapter pagerAdapter;

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
        positions = getArguments().getInt(ARG_POSITION);

        if(Data.getInstance().getCu_price().size() >0) {
            Util.getInstance().printLog(DEBUG, TAG, String.valueOf(Data.getInstance().getProduct_name()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        final View rootView;
        Util.getInstance().printLog(DEBUG, TAG, "page :" + pagerAdapter.getTitle(positions));
        rootView = inflater.inflate(R.layout.main, container, false);
        rootView.setBackgroundColor(Color.WHITE);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        CustomList customList = new CustomList(getActivity());
        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("positions", pagerAdapter.getTitle(positions));
                startActivity(intent);
            }
        });
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
            String price_str = "error!";
            View rowView= inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView manufac = (TextView) rowView.findViewById(R.id.manufac);
            TextView price = (TextView) rowView.findViewById(R.id.price);
            String name_str = Data.getInstance().getProduct_name().get(position);
            String manu_str = Data.getInstance().getManufac().get(position);
            title.setTextSize(30);
            title.setText(name_str);
            manufac.setTextSize(10);
            manufac.setText(manu_str);
            if(pagerAdapter.getTitle(positions).equals("seven_price")) {
                price_str = Data.getInstance().getSeven_price().get(position);
            }
            else if(pagerAdapter.getTitle(positions).equals("cu_price"))
            {
                price_str = Data.getInstance().getCu_price().get(position);
            }
            else
            {
                price_str = Data.getInstance().getGs_price().get(position);
            }

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

            price.setTextSize(15);
            price.setText(price_str + "원");
            return rowView;
        }
    }

}