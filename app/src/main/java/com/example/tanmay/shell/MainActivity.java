package com.example.tanmay.shell;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private final int SIGN_IN_CONST=1000;
    private int height, width;
    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDimensions();
        initialise();
        Click();
        full_screen();
        imageBackground();
    }

    private void display(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private void getDimensions(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
         width = size.x;
         height = size.y;
    }

    private void imageBackground(){
        String path="http://www.hostelbelgrade.com/images/hostel-belgrade-eye-03.jpg";
        int p = R.drawable.a;
        Glide.with(this).load(path).into(background);
    }
    private void full_screen(){
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initialise(){

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(googleSignInOptions.getScopeArray());
        signInButton.setColorScheme(SignInButton.COLOR_DARK);
        background = (ImageView)findViewById(R.id.background);
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();

    }

    private void Click(){
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(i, SIGN_IN_CONST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK&&requestCode==SIGN_IN_CONST){
            GoogleSignInResult acct = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(acct.isSuccess()){
                GoogleSignInAccount account = acct.getSignInAccount();
               // display("Name is : "+account.getDisplayName()+"\nEmail is: "+account.getEmail());
                Intent i = new Intent(this,Details.class);
                i.putExtra("name", account.getDisplayName());
                i.putExtra("email",account.getEmail());
                i.putExtra("icon",account.getPhotoUrl().toString());
                startActivity(i);
            }else{
                display("Invalid id & password");
            }
        }
    }

   

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        googleApiClient.connect();
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
