package com.example.tanmay.shell;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Upload extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar,menu_toolbar;
    DrawerLayout drawer;
    Animation slide_up, slide_down;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView games_text, movies_text, others_text, stationary_text,toolbar_name;
    ImageView other_image,stationary_image, movies_image,games_image;
    Context context;
    String name,profile_ic;
    CircleImageView profile_pic;
    //for edittext dynamically
    RequestQueue s;
    String[] modeText = {"Other Stuff ", " Movies ", "Games ", "Stationary"};
    Button upoad_toolbar_button,Upload;
    SharedPreferences sharedPreferences;
    LinearLayout linearLayout;
    EditText editText;
    Map<String,String>[] params;
    EditText up;
    String mainName="",number;
    int mode,i,inc=1;
    List<EditText> allEds  =new ArrayList<>();
    static int TotalEditText=0;
    StringBuilder  builder = new StringBuilder();;
    ProgressDialog dialog;
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

        dynamic_edittext_onclick();
        getData();
        setData();
            }

    private void dynamic_edittext_onclick() {
        upoad_toolbar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalEditText++;
                if (TotalEditText > 100) {
                    return;
                } else {
                    String[] modeText = {"Other Stuff ", " Movies ", "Games ", "Stationary"};
                    editText = new EditText(getBaseContext());
                    allEds.add(editText);
                    editText.setHint("Enter " + modeText[mode - 1] + "Name " + TotalEditText);
                    linearLayout.addView(editText);
                    editText.setGravity(Gravity.TOP);
                    editText.setTextColor(Color.BLACK);
                    editText.setId(TotalEditText);
                    editText.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                    LinearLayout.LayoutParams layoutparams = (LinearLayout.LayoutParams) editText.getLayoutParams();
                    layoutparams.setMargins(23, 24, 0, 0);
                    editText.setText("");
                    editText.setLayoutParams(layoutparams);
                    display(TotalEditText + "");

                }
            }
        });

    }

    private void getData(){
        name = sharedPreferences.getString("name","User");
        number = sharedPreferences.getString("number","null");
        profile_ic = sharedPreferences.getString("profile_pic","http://vignette3.wikia.nocookie.net/jamesbond/images/e/ec/James_Bond_Faceless_Profile.png");

    }

    private void setData(){
        toolbar_name.setText("Hello, "+name);
        Glide.with(this).load(profile_ic).into(profile_pic);
    }

    private void display_log(String s){
        Log.e("info", s);
    }

    private void doAnimation(View v, Animation m){
        v.startAnimation(m);
    }



    private void Click() {
        //final int TotalEditText = this.TotalEditText+1;
        final String[] modeText = {"Other Stuff ", " Movies ", "Games ", "Stationary"};
        other_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 1;
                up.setHint("Enter " + modeText[mode - 1] + "Name " + TotalEditText);
                doAnimation(Upload.this.menu_toolbar, slide_up);
            }
        });

        movies_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 2;

                up.setHint("Enter Movie " + TotalEditText);
                if (menu_toolbar.getAlpha() == 0)
                    menu_toolbar.setAlpha(1);
                up.setHint("Enter " + modeText[mode - 1] + "Name " + TotalEditText);
                doAnimation(Upload.this.menu_toolbar, slide_up);
            }
        });

        stationary_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 3;
                doAnimation(Upload.this.menu_toolbar, slide_up);
                up.setHint("Enter " + modeText[mode - 1] + "Name " + TotalEditText);
            }
        });

        games_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = 4;
                doAnimation(Upload.this.menu_toolbar, slide_up);
                up.setHint("Enter " + modeText[mode - 1] + "Name " + TotalEditText);
            }
        });


        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed;

                inc= 1;
                final int m = TotalEditText + 1;


                for (i = 0; i <= TotalEditText; i++) {

                    if (i == 0) {
                        ed = (EditText) findViewById(R.id.first);
                    } else {
                        ed = allEds.get(i - 1);
                    }
                    String mainName = ed.getText().toString();
                    display_log("Uploading " + inc + " of " + m);

                    Upload_Request(mainName, i, m);
                }
            }
        });
    }

    private void Upload_Request(final String name,final int i,final int size){

        StringRequest sr = new StringRequest(Request.Method.POST, Constants.USER_UPLOAD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                display(response+" for "+i);
                if(inc>=size)
                    dialog.dismiss();
                else {

                    dialog.setMessage("Uploading " + inc + " of " + size);
                    dialog.show();
                    display_log("I ran "+inc);
                    inc++;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                display(error.toString());
            }
        }){
            @Override
            protected Map getParams(){
                String type =  modeText[mode - 1];
                String time = System.currentTimeMillis()+"";
                Map<String, String > map = new HashMap<>();
                map.put("type",type);
                map.put("name",name);
                map.put("phone",number);
                map.put("time",time);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(sr);
    }

    private void display(String s){
       // Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void setChildTypeface(){
        setTypeFace(this.games_text);
        setTypeFace(this.movies_text);
        setTypeFace(this.stationary_text);
        setTypeFace(this.others_text);
        setTypeFace(this.toolbar_name);
    }

    private void setTypeFace(View v){

        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/android.ttf");
        ((TextView)v).setTypeface(t);
    }


    private void initialise(){
        s = Volley.newRequestQueue(getApplicationContext());
        dialog = new ProgressDialog(this);
        Upload = (Button)findViewById(R.id.upload);
        upoad_toolbar_button=(Button)findViewById(R.id.toolbar_button_Upload);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        toolbar_name = (TextView)findViewById(R.id.toolbar_content_upload);
        games_text = (TextView)findViewById(R.id.games_text);
        movies_text = (TextView)findViewById(R.id.movies_text);
        others_text = (TextView)findViewById(R.id.others_text);
        stationary_text = (TextView)findViewById(R.id.stationary_text);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        menu_toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        slide_down = AnimationUtils.loadAnimation(this,R.anim.slide_down);
        slide_up = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        sharedPreferences = getSharedPreferences(Constants.USER_DATA_SHARED_PREF,MODE_PRIVATE);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        other_image = (ImageView)findViewById(R.id.imageViewOthers);
        movies_image = (ImageView)findViewById(R.id.imageViewMovie);
        stationary_image = (ImageView)findViewById(R.id.imageViewStationary);
        games_image = (ImageView)findViewById(R.id.imageViewGame);
        View v =navigationView.getHeaderView(0);
        profile_pic=(CircleImageView)v.findViewById(R.id.circleImageView);
        up = (EditText)findViewById(R.id.first);
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
