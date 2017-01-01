package com.example.tanmay.shell;


public class Album {
    private String name;
    private int thumbnail=R.mipmap.ic_launcher;
    private String time;

    public Album(String name, int thumbnail)
    {
        this.name=name;
        this.thumbnail=thumbnail;
    }

    public Album(String name, int thumbnail, String time)
    {
        this.time=time;
        this.name=name;
        this.thumbnail=thumbnail;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getThumbnail() {
        return thumbnail;
    }

    public String getTime() {
        return time;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}