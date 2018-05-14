package com.example.mauro.amplis;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorFragmentPager extends FragmentPagerAdapter{
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    final int PAGE_COUNT = 3; //Cantidad de tabs
   // private String tabTitles[] = new String[] { "Equipo", "Descripcion", "Codigo interno"};

    public AdaptadorFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
        //return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
//oliver

        Fragment f = null;

        switch(position) {
            case 0:
                f = EquipoFragment.newInstance();
                break;
            case 1:
                f = DescripcionFragment.newInstance();
                break;
            case 2:
                f = CodigoFragment.newInstance();
                break;
        }

        return f;

    }

/*
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        //return tabTitles[position];
        return mFragmentTitleList.get(position);
    }
*/

}
