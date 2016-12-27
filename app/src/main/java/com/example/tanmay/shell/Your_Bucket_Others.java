package com.example.tanmay.shell;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by PRANSHOO VERMA on 24-12-2016.
 */

public class Your_Bucket_Others extends Fragment {

    SharedPreferences sharedPreferences;
    String user_phone;
    static ArrayList<String> name_others=new ArrayList<String>();
    static ArrayList<String> time_others=new ArrayList<String>();
    RecyclerView recyclerView;
    AlbumAdapter adapter;
    List<Album> albumList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.your_bucket_others,container,false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        sharedPreferences = getActivity().getSharedPreferences(Constants.USER_DATA_SHARED_PREF,MODE_PRIVATE);
        user_phone = sharedPreferences.getString("number","1234");
        albumList=new ArrayList<>();

        fetch_others();

        return v;

    }


    void fetch_others()
    {

        final String gh= "http://pranshooverma1234.site88.net/shell/recieve_other.php\n";

        StringRequest st=new StringRequest(Request.Method.POST, gh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
           //     Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray js=jsonObject.getJSONArray("other_respose");

                    for(int i=0;i<js.length();i++)
                    {
                        JSONObject c=js.getJSONObject(i);

                        name_others.add(c.getString("name"));
                        time_others.add(c.getString("time"));

                      //  Toast.makeText(Your_Bucket.this, name_others[i]+""+time_others[i], Toast.LENGTH_SHORT).show();
                    }


                    int i=0;
                    while (i < js.length())
                    {
                        Album a = new Album(name_others.get(i), R.mipmap.ic_launcher);
                        albumList.add(a);
                        i++;
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
