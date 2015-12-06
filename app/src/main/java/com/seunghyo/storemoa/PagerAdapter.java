package com.seunghyo.storemoa;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by SeungHyo on 2015-12-06.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final String[] TITLES = {"세븐일레븐", "CU", "GS25"};

    private final String[] TITLE = {"seven_price", "cu_price", "gs_price"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public String getTitle(int i) { return  TITLE[i]; }
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return SuperAwesomeCardFragment.newInstance(position);
    }

}

