package com.example.tanmay.shell;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Upload extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar,menu_toolbar;
    DrawerLayout drawer;
    Animation slide_up, slide_down;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView games_text, movies_text, others_text, stationary_text;
    ImageView other_image,stationary_image, movies_image,games_image;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
         initialise();
        //set others image
        setOther_image();
        //slide down the menu_toolbar

        setChildTypeface();
        Click();
    }

    private void doAnimation(View v, Animation m){
        v.startAnimation(m);
    }

    private void Click() {
       final Intent i = new Intent(this,upload_form.class);
        other_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("others",1);
                doAnimation(Upload.this.menu_toolbar,slide_up);
            }
        });

        movies_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("movies",2);
                if(menu_toolbar.getAlpha()==0)
                    menu_toolbar.setAlpha(1);
                doAnimation(Upload.this.menu_toolbar, slide_up);
            }
        });

        stationary_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("stationary",3);
                doAnimation(Upload.this.menu_toolbar, slide_up);
            }
        });

        games_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("games", 4);
                doAnimation(Upload.this.menu_toolbar,slide_up);
            }
        });
    }

    private void setChildTypeface(){
        setTypeFace(this.games_text);
        setTypeFace(this.movies_text);
        setTypeFace(this.stationary_text);
        setTypeFace(this.others_text);
    }

    private void setTypeFace(View v){

        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/android.ttf");
        ((TextView)v).setTypeface(t);
    }

    private void initialise(){

        games_text = (TextView)findViewById(R.id.games_text);
        movies_text = (TextView)findViewById(R.id.movies_text);
        others_text = (TextView)findViewById(R.id.others_text);
        stationary_text = (TextView)findViewById(R.id.stationary_text);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        menu_toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        slide_down = AnimationUtils.loadAnimation(this,R.anim.slide_down);
        slide_up = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        other_image = (ImageView)findViewById(R.id.imageViewOthers);
        movies_image = (ImageView)findViewById(R.id.imageViewMovie);
        stationary_image = (ImageView)findViewById(R.id.imageViewStationary);
        games_image = (ImageView)findViewById(R.id.imageViewGame);
    }

    private void setOther_image(){
        int path = R.drawable.others;
        Glide.with(this).load(path).into(other_image);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.upload, menu);
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


    //  @SuppressWarnings("StatementWithEmptyBody")
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
