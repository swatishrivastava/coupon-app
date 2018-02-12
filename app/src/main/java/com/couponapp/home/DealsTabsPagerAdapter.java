package com.couponapp.home;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.couponapp.home.category.CategoryFragment;
import com.couponapp.home.deals.AllDealsFragment;
import com.couponapp.home.deals.DealPojo;

import java.util.ArrayList;


public class DealsTabsPagerAdapter extends FragmentPagerAdapter {

    public DealsTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return getAllDealsTabFragment();
            case 1:
                return getCategoryTabFragment();
            default:
                return null;
        }
    }


    private AllDealsFragment getAllDealsTabFragment() {
        AllDealsFragment allDealsFragment = AllDealsFragment.newInstance();
        return allDealsFragment;
    }

    private CategoryFragment getCategoryTabFragment() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
