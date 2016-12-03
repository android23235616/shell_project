package com.example.tanmay.shell;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Details extends AppCompatActivity {

    CircleImageView profile_pic;
    Toolbar toolbar;
    TextView Next, Back,Name,toolBarTitle;
    Spinner hostel_spinner;
    EditText phone;
    Intent i;
    LinearLayout editText_wrapper, spinner_wrapper;
    Animation blink,slide_left;
    private int tracker=0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String name, number, hostel, room, email,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initialise();
        spinner_wrapper.setVisibility(View.GONE);
        i = getIntent();
        if(i!=null){
            setData(i);
        }else{
            setNullData();
        }
        registrationAction();
        setAnimations(this.blink,this.Name);
        Click();
        setChildTypeface();
        setUpSpinner();
        spinnerItemClick();
    }

    private void registrationAction() {
        if(isRegistered()){
            display("This device is registered");
        }else{
            display("This device is not registered");

        }
    }

    private void registerDevice(){
        Firebase firebase = new Firebase(Constants.FIREBASE_URL);
        Firebase newFirebase = firebase.push();
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("email",email);
        map.put("phone",number);
        map.put("hostel",hostel);
        map.put("room", room);
        newFirebase.setValue(map);
        String id = newFirebase.getKey();
        sendToServer(id);

    }

    private void sendToServer(final String id) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading data to server...");
        progressDialog.show();
        StringRequest sr = new StringRequest(Request.Method.POST, Constants.USER_REGISTRATION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(response.equals("success")){
                    //device has been registered
                    editor.putBoolean(Constants.REGISTERED, true);
                    editor.apply();
                    display("Data uploaded successfully");
                }else{
                    display("Unable to upload data");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                display(error.toString());
            }

        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("name",name);
                map.put("email",email);
                map.put("phone",number);
                map.put("hostel",hostel);
                map.put("room",room);
                map.put("token",id);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);

        sr.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private void display(String s) {

        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private boolean isRegistered(){
        return sharedPreferences.getBoolean(Constants.REGISTERED,false);
    }

    private void spinnerItemClick() {
       hostel_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               hostel = parent.getItemAtPosition(position).toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }

    private void setUpSpinner() {
        List<String> hostel_adapter = new ArrayList<>();
        for(int i=1; i<=12; i++)
            hostel_adapter.add("KP-"+i);
        for(int i=1; i<=6; i++)
            hostel_adapter.add("QC-"+i);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_text_view,hostel_adapter);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_text_view);
        hostel_spinner.setAdapter(arrayAdapter);
    }

    

    private void setAnimations(Animation blink, View profile_pic) {
        profile_pic.startAnimation(blink);
    }

    private void setNullData() {
        name="Tanmay";
        email="majumdartanmay68@gmail.com";
        Name.setText("Hello, Tanmay");
        String path="http://cdn1.blog-media.zillowstatic.com/1/Robert-Pattinson-IMDb-bd3782.jpg";
        Glide.with(this).load(path).into(profile_pic);
    }

    private void setChildTypeface(){
        setTypeFace(this.Name);
        setTypeFace(this.Back);
        setTypeFace(this.Next);
        setTypeFace(this.toolBarTitle);
    }
    private void Click(){
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tracker++;
                setAnimations(slide_left,hostel_spinner);
                setAnimations(slide_left, phone);
                if(tracker==1)
                    number = phone.getText().toString();
                else
                    room = phone.getText().toString();
                phone.setText("");
                phone.setHint("Enter Room Number:  ");
                TrackerVisibilityChecker(tracker);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tracker--;
                setAnimations(slide_left, hostel_spinner);
                room = phone.getText().toString();
                phone.setText("");
                phone.setHint("Phone Number: ");
                setAnimations(slide_left, phone);
                TrackerVisibilityChecker(tracker);
            }
        });
    }

    private void TrackerVisibilityChecker(int tracker) {
        if(tracker==0){

            editText_wrapper.setVisibility(View.VISIBLE);
            spinner_wrapper.setVisibility(View.GONE);
            Next.setVisibility(View.VISIBLE);
            Back.setVisibility(View.INVISIBLE);
        }else if(tracker==1)
        {
            printLog("Reacher here at traker 1");
            editText_wrapper.setVisibility(View.GONE);
            spinner_wrapper.setVisibility(View.VISIBLE);
            Next.setVisibility(View.VISIBLE);
            Back.setVisibility(View.VISIBLE);
        }else if(tracker==2){
            editText_wrapper.setVisibility(View.VISIBLE);
            spinner_wrapper.setVisibility(View.GONE);
            Next.setText("Done");
            Back.setVisibility(View.VISIBLE);
        }else{
            registerDevice();

            printLog("Name: " + name + " number: " + number + " email: " + email + " hostel: " + hostel + " room: " + room);
        }
    }

    private void printLog(String s) {
        Log.i("info 1",s);
    }

    private void setData(Intent b) {
        String url = b.getStringExtra("icon");

        String n="null";
        n = b.getStringExtra("name");
        name=n;
        email = b.getStringExtra("email");
        if (n==null) {
            setNullData();
        } else {
            Name.setText("Hello, " + n);
            Glide.with(this).load(url).into(profile_pic);
        }
    }

    private void initialise(){
        Name = (TextView)findViewById(R.id.name);
        profile_pic =(CircleImageView)findViewById(R.id.profile_pic);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        phone = (EditText)findViewById(R.id.number);
        Next = (TextView)findViewById(R.id.next);
        Back = (TextView)findViewById(R.id.Previous);
        toolBarTitle = (TextView)findViewById(R.id.toolbarTitle);
        hostel_spinner = (Spinner)findViewById(R.id.hostel_spinner);
        blink = AnimationUtils.loadAnimation(this,R.anim.blink);
        slide_left = AnimationUtils.loadAnimation(this,R.anim.slide_left);
        editText_wrapper = (LinearLayout)findViewById(R.id.edittext_wrapper);
        spinner_wrapper = (LinearLayout)findViewById(R.id.spinner_wrapper);
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF,MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void setTypeFace(View v){

        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/android.ttf");
        ((TextView)v).setTypeface(t);
    }


}
