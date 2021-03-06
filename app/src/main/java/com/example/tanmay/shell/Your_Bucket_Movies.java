package com.example.tanmay.shell;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class Your_Bucket_Movies extends Fragment {
    SharedPreferences sharedPreferences;
    String user_phone;
    static ArrayList<String> name_movie=new ArrayList<String>();
    static ArrayList<String> time_movie=new ArrayList<String>();
    RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private List<Album> albumList;
    String time[]=new String[20000];
    String time_movies_span[]=new String[20000];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater infla, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= infla.inflate(R.layout.your_bucket_movies, container,false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        sharedPreferences = getActivity().getSharedPreferences(Constants.USER_DATA_SHARED_PREF,MODE_PRIVATE);
        user_phone = sharedPreferences.getString("number","1234");
        albumList=new ArrayList<>();


        fetch_movie();





        return v;
    }


    void fetch_movie()
    {
        final String gh="http://pranshooverma1234.site88.net/shell/recieve_movie.php";

        StringRequest st=new StringRequest(Request.Method.POST, gh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray js = jsonObject.getJSONArray("movie_respose");

                    for (int i = 0; i < js.length(); i++) {
                        JSONObject c = js.getJSONObject(i);

                        name_movie.add(c.getString("name"));
                 //       time_movie.add(c.getString("time"));

                        time[i]=c.getString("time");
                        time_movies_span[i]= String.valueOf(DateUtils.getRelativeTimeSpanString(Long.parseLong(time[i]),System.currentTimeMillis(),DateUtils.SECOND_IN_MILLIS));


                    }

                    int i = 0;

                    while (i < js.length())
                    {
                        Album a = new Album(name_movie.get(i), R.mipmap.ic_launcher,time_movies_span[i]);
                        albumList.add(a);
                        i++;
                        Toast.makeText(getContext(),time_movies_span[i],Toast.LENGTH_SHORT).show();
                    }
                    adapter=new AlbumAdapter(getContext(),albumList);
                    RecyclerView.LayoutManager mLayout=new GridLayoutManager(getContext(),2);
                    recyclerView.setLayoutManager(mLayout);
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    Toast.makeText(getContext(),"Error occured in catch", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }




        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error Occured,Try Again!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();


                params.put("phone",user_phone);

                return params;
            }

        };
        RequestQueue re= Volley.newRequestQueue(getContext());
        re.add(st);
    }


}
