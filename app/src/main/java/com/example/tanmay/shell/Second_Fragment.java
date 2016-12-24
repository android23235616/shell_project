package com.example.tanmay.shell;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Tanmay on 23-12-2016.
 */
public class Second_Fragment extends Fragment {

    int page;
    String title;

    public static Second_Fragment newInstance(int page, String title){
        Second_Fragment first_fragment = new Second_Fragment();
        Bundle arg = new Bundle();
        arg.putInt("key1", page);
        arg.putString("key2", title);
        first_fragment.setArguments(arg);
        return first_fragment;
    }

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        page = getArguments().getInt("key1",0);
        title = getArguments().getString("key2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_menu_fragment2,group,false);
        TextView t = (TextView)view.findViewById(R.id.FragmentText2);
        t.append((page+1)+"");
        return view;
    }
}
