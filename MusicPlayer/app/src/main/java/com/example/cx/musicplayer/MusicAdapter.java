package com.example.cx.musicplayer;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {
    private int resourceId;
    public MusicAdapter(Context context, int textViewResourceId, List<Music> objects){
        super (context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Music mu =getItem(position);
        View view =LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView mName=(TextView) view.findViewById(R.id.musicname);
        TextView aName=(TextView) view.findViewById(R.id.artistname);
        mName.setText(mu.getTitle());
        aName.setText(mu.getArtist());
        mName.setTextSize(20);
        mName.setTextColor(Color.BLUE);
        return view;
    }

}
