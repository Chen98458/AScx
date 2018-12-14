package com.example.cx.beiwanglu;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.search);
        TextView titleEditText = (TextView) SearchActivity.this.findViewById(R.id.titles);
        TextView contentEditText = (TextView) SearchActivity.this.findViewById(R.id.contents);
        TextView creatimes = (TextView) SearchActivity.this.findViewById(R.id.creatimes);
        int id = this.getIntent().getIntExtra("id",1);
        Cursor cursor = DBService.queryNoteById((int) id);
        if (cursor.moveToFirst()) {
            // 将内容提取出来
            titleEditText.setText(cursor.getString(cursor.getColumnIndex("title")));
            contentEditText.setText(cursor.getString(cursor.getColumnIndex("content")));
            creatimes.setText(cursor.getString(cursor.getColumnIndex("createTime")));
        }
    }
}