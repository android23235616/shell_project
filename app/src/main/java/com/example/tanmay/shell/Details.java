package com.example.tanmay.shell;

import android.content.Intent;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

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
        setAnimations(this.blink,this.Name);
        Click();
        setChildTypeface();
        setUpSpinner();
        spinnerItemClick();
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
                number = phone.getText().toString();
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
            printLog("Name: ");
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
    }

    private void setTypeFace(View v){

        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/android.ttf");
        ((TextView)v).setTypeface(t);
    }

    private void remove_status_bar(){
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
