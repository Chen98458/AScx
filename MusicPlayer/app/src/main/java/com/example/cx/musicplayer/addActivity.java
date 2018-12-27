package com.example.cx.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addActivity extends AppCompatActivity {
    EditText editText;
    String aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button button =(Button)  findViewById(R.id.right);
        editText  =(EditText)findViewById(R.id.Edit1);
        aa= editText.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent =new Intent(addActivity.this,Main.class);
                //intent.putExtra("AA",aa);
                //startActivity(intent);
                Main main=new Main();
                finish();;
            }
        });
    }
}
