package com.couponapp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class DealsTabsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public DealsTabsPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    public void addFragments(Fragment fragment) {
        fragmentList.add(fragment);

    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
