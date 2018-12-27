package com.example.cx.musicplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Music> musicList = new ArrayList<>();
    private ListView listView;
    MusicAdapter adapter;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    EditText editText;
    String a = "";
    String a1 = "";
    String a2 = "";
    private SeekBar seekBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        Button select = (Button) findViewById(R.id.select);
        Button SYS = (Button) findViewById(R.id.SYS);
        Button XYS = (Button) findViewById(R.id.XYS);
        seekBar1 = (SeekBar) findViewById(R.id.sb);
        editText = (EditText) findViewById(R.id.Edit);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        select.setOnClickListener(this);
        SYS.setOnClickListener(this);
        XYS.setOnClickListener(this);
        Intent intent = getIntent();
        a = intent.getStringExtra("name");
        a1 = intent.getStringExtra("name1");
        a2 = intent.getStringExtra("name2");
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPlayer();
        }
    }

    private void sing() {
        a1 = editText.getText().toString();                                            //获取输入内容
        Toast.makeText(this, "选择成功", Toast.LENGTH_SHORT).show();
        initMediaPlayer1();
    }

    private void initMediaPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "/storage/emulated/0/qqmusic/song/" + a + ".mp3");
            mediaPlayer.setDataSource(a);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        a();
    }
    private void initMediaPlayer1() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "/storage/emulated/0/qqmusic/song/" + a1 + ".mp3");
            mediaPlayer.setDataSource(a1);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        a();
    }
    private void initMediaPlayer2() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "/storage/emulated/0/qqmusic/song/" + a2 + ".mp3");
            mediaPlayer.setDataSource(a2);
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        a();
    }

    public void a(){}
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝全限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }








        public void onClick (View v){
        switch (v.getId()) {
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.select:
                sing();
                break;
            case R.id.SYS:
                mediaPlayer.reset();
               initMediaPlayer1();
               break;
            case R.id.XYS:
                mediaPlayer.reset();
                initMediaPlayer2();
                break;
            default:
                break;
        }
    }
        protected void onDestroy () {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

}