package com.example.mauro.amplis;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorFragmentPager extends FragmentPagerAdapter{
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();


    public AdaptadorFragmentPager(FragmentManager fm) {

        super(fm);
    }

    @Override
    public int getCount() {

        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mFragmentTitleList.get(position);
    }

}
