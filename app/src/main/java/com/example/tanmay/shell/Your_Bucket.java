package com.example.tanmay.shell;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Your_Bucket extends AppCompatActivity {
private TabLayout tabLayout;
     ViewPager viewPager;
    SharedPreferences sharedPreferences;
    String user_phone;


    //##TO SOUMYADEB//
//in these array parsed data gets saved/
    ArrayList<String> name_movie=new ArrayList<String>();
    ArrayList<String> time_movie=new ArrayList<String>();
/*
    String[] name_tv=new String[10000];
    String[] time_tv=new String[10000];

    String[] name_games=new String[10000];
    String[] time_games=new String[10000];

    String[] name_stationary=new String[10000];
    String[] time_stationary=new String[10000];

    String[] name_others=new String[10000];
    String[] time_others=new String[10000];
*/
//OVER//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your__bucket);
        //getting user's phone number
        sharedPreferences = getSharedPreferences(Constants.USER_DATA_SHARED_PREF,MODE_PRIVATE);
        user_phone = sharedPreferences.getString("number","1234");

        initialize();
        setAdapter();

    }

    void initialize()
    {
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.pager);

    }

/*
    void fetch_movie()
    {
        final String gh="http://pranshooverma1234.site88.net/shell/recieve_movie.php";

        StringRequest st=new StringRequest(Request.Method.POST, gh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Your_Bucket.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray js=jsonObject.getJSONArray("movie_respose");

                    for(int i=0;i<js.length();i++)
                    {
                        JSONObject c=js.getJSONObject(i);

                        name_movie.add(c.getString("name"));
                        time_movie.add(c.getString("time"));

                        Toast.makeText(Your_Bucket.this, name_movie.get(i)+""+time_movie.get(i), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Error occured in catch", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Your_Bucket.this, "Error Occured,Try Again!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();


                params.put("phone",user_phone);

                return params;
            }
        };
        RequestQueue re= Volley.newRequestQueue(getApplicationContext());
        re.add(st);
    }


    void fetch_tv_series()
    {


        final String gh= "http://pranshooverma1234.site88.net/shell/recieve_tv.php";

        StringRequest st=new  StringRequest(Request.Method.POST, gh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Your_Bucket.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray js=jsonObject.getJSONArray("tv_respose");

                    for(int i=0;i<js.length();i++)
                    {
                        JSONObject c=js.getJSONObject(i);

                        name_tv[i] =c.getString("name");
                        time_tv[i]=c.getString("time");

                        Toast.makeText(Your_Bucket.this, name_tv[i]+""+time_tv[i], Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Error occured in catch", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Your_Bucket.this, "Error Occured,Try Again!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();


                params.put("phone",user_phone);

                return params;
            }
        };
        RequestQueue re= Volley.newRequestQueue(getApplicationContext());
        re.add(st);
    }



    void fetch_games()
    {

        final String gh= "http://pranshooverma1234.site88.net/shell/recieve_game.php\n";

        StringRequest st=new StringRequest(Request.Method.POST, gh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Your_Bucket.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray js=jsonObject.getJSONArray("game_respose");

                    for(int i=0;i<js.length();i++)
                    {
                        JSONObject c=js.getJSONObject(i);

                        name_games[i] =c.getString("name");
                        time_games[i]=c.getString("time");

                        Toast.makeText(Your_Bucket.this, name_games[i]+""+time_games[i], Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Error occured in catch", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Your_Bucket.this, "Error Occured,Try Again!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();


                params.put("phone",user_phone);

                return params;
            }
        };
        RequestQueue re= Volley.newRequestQueue(getApplicationContext());
        re.add(st);

    }
    void fetch_stationary()
    {

        final String gh= "http://pranshooverma1234.site88.net/shell/recieve_stationery.php";

        StringRequest st=new StringRequest(Request.Method.POST, gh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Your_Bucket.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray js=jsonObject.getJSONArray("stationary_respose");

                    for(int i=0;i<js.length();i++)
                    {
                        JSONObject c=js.getJSONObject(i);

                        name_stationary[i] =c.getString("name");
                        time_stationary[i]=c.getString("time");

                        Toast.makeText(Your_Bucket.this, name_stationary[i]+""+time_stationary[i], Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Error occured in catch", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Your_Bucket.this, "Error Occured,Try Again!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();


                params.put("phone",user_phone);

                return params;
            }
        };
        RequestQueue re= Volley.newRequestQueue(getApplicationContext());
        re.add(st);

    }



    void fetch_others()
    {

        final String gh= "http://pranshooverma1234.site88.net/shell/recieve_other.php\n";

        StringRequest st=new StringRequest(Request.Method.POST, gh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Your_Bucket.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray js=jsonObject.getJSONArray("other_respose");

                    for(int i=0;i<js.length();i++)
                    {
                        JSONObject c=js.getJSONObject(i);

                        name_others[i] =c.getString("name");
                        time_others[i]=c.getString("time");

                        Toast.makeText(Your_Bucket.this, name_others[i]+""+time_others[i], Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Error occured in catch", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Your_Bucket.this, "Error Occured,Try Again!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();


                params.put("phone",user_phone);

                return params;
            }
        };
        RequestQueue re= Volley.newRequestQueue(getApplicationContext());
        re.add(st);

    }


*/

    void setAdapter() {
        Your_Bucket_ADAPTER adapter=new Your_Bucket_ADAPTER(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        viewPager.setPageTransformer(true,new RotateUpTransformer());

     /*   tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            switch (tab.getPosition())
                {
                    case 0:fetch_movie();
                        break;
                    case 1:fetch_tv_series();
                        break;

                    case 2:fetch_games();
                        break;

                    case 3:fetch_stationary();
                        break;

                    case 4:fetch_others();
                        break;

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

    }



}
