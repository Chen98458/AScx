package com.example.cx.musicplayer;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<music> {
    private int resourceId;
    public MusicAdapter(Context context, int textViewResourceId, List<music> objects){
        super (context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        music mu =getItem(position);
        View view =LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView mName=(TextView) view.findViewById(R.id.musicname);
        mName.setText(mu.getName());
        return view;

    }

}
