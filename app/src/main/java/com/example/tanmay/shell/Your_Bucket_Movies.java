package com.example.tanmay.shell;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PRANSHOO VERMA on 24-12-2016.
 */

public class Your_Bucket_Movies extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater infla, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return infla.inflate(R.layout.your_bucket_movies, container,false);
    }
}
