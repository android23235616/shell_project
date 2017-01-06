package com.example.tanmay.shell;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,time;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title= (TextView) view.findViewById(R.id.title);
            time= (TextView) view.findViewById(R.id.time);
            thumbnail= (ImageView) view.findViewById(R.id.thumbnail);
        }

    }

    public AlbumAdapter(Context mContext, List<Album> albumList)
    {
        this.mContext=mContext;
        this.albumList=albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumAdapter.MyViewHolder holder, int position)
    {
        Album album=albumList.get(position);
        holder.title.setText(album.getName());
        holder.time.setText(album.getTime());
       // holder.thumbnail.setImageResource(album.getThumbnail());
       Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


}