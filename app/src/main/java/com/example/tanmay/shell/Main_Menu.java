package com.example.tanmay.shell;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;

public class Main_Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Main_Menu_Page_Adapter pageAdapter;
    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
        initialise();
        navigationView.setNavigationItemSelectedListener(this);
        viewPager.setPageTransformer(true,new CubeOutTransformer());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initialise(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        viewPager = (ViewPager)findViewById(R.id.vPager);
        pageAdapter= new Main_Menu_Page_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.send_request) {
            // Handle the camera action
        } else if (id == R.id.received_request) {
            Toast.makeText(this, "Received Request pressed", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.Profile) {

        } else if (id == R.id.logout)
        {

        }
        else if(id==R.id.home)
        {

        }
        else if(id==R.id.Uploading)
        {

        }
        else if(id==R.id.bucket)
        {
            startActivity(new Intent(this, Your_Bucket.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class Main_Menu_Page_Adapter extends FragmentPagerAdapter{
        private String tabTitles[] = new String[] { "MOVIES","TV SERIES", "GAMES", "STATIONERY","OTHERS" };

        public Main_Menu_Page_Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return  new MoviesFragment();
                   // return First_Fragment.newInstance(0,"Movies");
                case 1:
                  //  return First_Fragment.newInstance(1,"Games");
                    return new TVFragment();
                case 2:
                    return  new GamesFragment();
                   // return First_Fragment.newInstance(2,"Stationary");
                case 3:
                    return new StationaryFragment();
                  //  return First_Fragment.newInstance(3,"Others");
                case 4:
                    return  new OthersFragment();
            }
            return null;
        }

       @Override
        public CharSequence getPageTitle(int position){
           return tabTitles[position];
        }

    }

}
