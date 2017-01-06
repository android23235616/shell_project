package com.example.tanmay.shell;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class Your_Bucket_ADAPTER extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"Movies", "TV Series","Games" ,"Stationery","Others"};

    public Your_Bucket_ADAPTER(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                return new Your_Bucket_Movies();

            case 1:
                return new Your_Bucket_TV_series();

            case 2:
                return new Your_Bucket_Games();

            case 3:
                return new Your_Bucket_Stationary();
            case 4:
                return new Your_Bucket_Others();
            default :
                    return new Your_Bucket_Movies();
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
