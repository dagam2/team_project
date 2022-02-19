package com.project.lego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       VideoView video = (VideoView) findViewById(R.id.videoView);
       Uri uri = Uri.parse("android.resource://com.project.lego/" + R.raw.mv1);
       video.setVideoURI(uri);
       video.start();

       MediaPlayer mp = MediaPlayer.create(this, R.raw.mv1);
       mp.start();

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);

            }
        });
    }
}