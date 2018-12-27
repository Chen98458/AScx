package com.example.cx.musicplayer;

public class Music {
    private long id;
    private String title;
    private String artist;
    private long duration;
    private long size;
    private String path;
    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public void setArtist(String artist){
        this.artist=artist;
    }
    public String getArtist(){
        return artist;
    }
    public void setDuration(long duration){
        this.duration=duration;
    }
    public long getDuration(){
        return duration;
    }
    public void setSize(long size){
        this.size=size;
    }
    public long getSize(){
        return size;
    }
    public void setPath(String path){
        this.path=path;
    }
    public String getPath(){
        return path;
    }

}
