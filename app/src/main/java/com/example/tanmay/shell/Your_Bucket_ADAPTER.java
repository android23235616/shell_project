package com.example.tanmay.shell;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by PRANSHOO VERMA on 24-12-2016.
 */

public class Your_Bucket_ADAPTER extends FragmentStatePagerAdapter {

    private String[] tabTitles = new String[]{"Movies", "TV Series","Games" ,"stationary","others"};

    public Your_Bucket_ADAPTER(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                Your_Bucket_Movies a=new Your_Bucket_Movies();
                return a;

            case 1:
                Your_Bucket_TV_series b=new Your_Bucket_TV_series();
                return b;

            case 2:

                Your_Bucket_Sports c=new Your_Bucket_Sports();
                return c;

            case 3:

                Your_Bucket_Stationary d=new Your_Bucket_Stationary();
                return d;
            case 4:

                Your_Bucket_Others e=new Your_Bucket_Others();
                return e;

            default :
                    return null;
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
