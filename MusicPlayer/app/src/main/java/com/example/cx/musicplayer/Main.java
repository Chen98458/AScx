package com.example.cx.musicplayer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private List<Music> musicList =new ArrayList<>();
    private ListView listView;
    MusicAdapter adapter;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicList=getMusic();
        adapter =new MusicAdapter(Main.this,R.layout.music_item,musicList);
        listView =(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Music mm = musicList.get(position);
                Music mm1 = musicList.get(position-1);
                Music mm2 = musicList.get(position+1);
                Intent in = new Intent();
                in.setClassName(view.getContext(), "com.example.cx.musicplayer.MainActivity");
                in.putExtra("name",mm.getPath());
                in.putExtra("name1",mm1.getPath());
                in.putExtra("name2",mm2.getPath());
                startActivity(in);
                Toast.makeText(Main.this,"点击成功",Toast.LENGTH_LONG).show();
            }
        });
    }
 /*   public void Sys(){
        Music mm = musicList.get(position-1);
        Intent in = new Intent();
        in.setClassName(view.getContext(), "com.example.cx.musicplayer.MainActivity");
        in.putExtra("name",mm.getPath());
        startActivity(in);
    }*/


    private List<Music> getMusic() {       //获取列表
        List <Music> musicList = new ArrayList <>();
        Cursor cursor = this.getContentResolver().query( MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER );
        if (cursor.moveToFirst()){
            do {
                Music music = new Music();
                music.setId( cursor.getLong( cursor.getColumnIndex( MediaStore.Audio.Media._ID ) ) );
                music.setTitle( cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.TITLE ) ) );
                music.setArtist( cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.ARTIST ) ) );
                music.setDuration( cursor.getLong( cursor.getColumnIndex( MediaStore.Audio.Media.DURATION ) ) );
                music.setSize( cursor.getLong( cursor.getColumnIndex( MediaStore.Audio.Media.SIZE ) ) );
                music.setPath( cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.DATA ) ) );
                musicList.add( music );
            }while(cursor.moveToNext());
        }
        return musicList;
    }
}
