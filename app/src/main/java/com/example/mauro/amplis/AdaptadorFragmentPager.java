package com.example.mauro.amplis;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mauro.amplis.MarshallFragment;
import com.example.mauro.amplis.VoxFragment;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorFragmentPager extends FragmentPagerAdapter{
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    final int PAGE_COUNT = 3;
    private String tabTitles[] =
            new String[] { "Todos", "Filtro Marshall", "Filtro Vox"};

    public AdaptadorFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT; //SGoliver
        //return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
//oliver
        Fragment f = null;

        switch(position) {
            case 0:
                f = MarshallFragment.newInstance();
                break;
            case 1:
                f = MarshallFragment.newInstance();
                break;
            case 2:
                f = VoxFragment.newInstance();
                break;
        }

        return f;

        //return mFragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];

        //return mFragmentTitleList.get(position);
    }

}
