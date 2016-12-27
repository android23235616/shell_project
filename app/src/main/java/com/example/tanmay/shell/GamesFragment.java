package com.example.tanmay.shell;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class GamesFragment extends Fragment {

    RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private List<Album> albumList;

    public GamesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_movies, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);

        albumList=new ArrayList<>();
        adapter=new AlbumAdapter(getContext(),albumList);
        RecyclerView.LayoutManager mLayout=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setAdapter(adapter);



        prepareAlbums();

        return v;
    }
    private void prepareAlbums()
    {
        int[] movies=new int[]{R.drawable.gta,R.drawable.black_ops,R.drawable.witcher3,R.drawable.overwatch,R.drawable.warcraft,R.drawable.fifa};
        Album a=new Album("GTA-5",movies[0]);
        albumList.add(a);

        a=new Album("Black Ops 3",movies[1]);
        albumList.add(a);

        a=new Album("The Witcher-3",movies[2]);
        albumList.add(a);

        a=new Album("Overwatch",movies[3]);
        albumList.add(a);

        a=new Album("World of Warcraft",movies[4]);
        albumList.add(a);

        a=new Album("FIFA-16",movies[5]);
        albumList.add(a);


    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}