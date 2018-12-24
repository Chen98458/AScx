package com.example.cx.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    //private String [] data={"sxj","爱呀"};
    private List<music> musicList =new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                //Main.this,android.R.layout.simple_list_item_1,data);
        //initMusic();
        for(int i=0;i<1;i++){
            music m1= new music("sxj");
            musicList.add(m1);
            music m2 =new music("爱呀");
            musicList.add(m2);
        }

        MusicAdapter adapter =new MusicAdapter(Main.this,R.layout.music_item,musicList);
        ListView listView =(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int position, long id) {
                music mm = musicList.get(position);
                Intent in = new Intent();
                in.setClassName(view.getContext(), "com.example.cx.musicplayer.MainActivity");
                in.putExtra("name",mm.getName());
                startActivity(in);
                Toast.makeText(Main.this,"点击成功",Toast.LENGTH_LONG).show();
            }
        });

    }

    /*private void initMusic(){
    for(int i=0;i<1;i++){
        music m1= new music("sxj");
        musicList.add(m1);
        music m2 =new music("爱呀");
        musicList.add(m2);
       }
    }*/

}
