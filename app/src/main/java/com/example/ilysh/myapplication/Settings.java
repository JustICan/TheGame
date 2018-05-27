package com.example.ilysh.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch switchMusic;
    Button buttonToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonToHome = (Button) findViewById(R.id.buttonToHome);
        buttonToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Settings.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        switchMusic = (Switch) findViewById(R.id.switchMusic);
        if (switchMusic != null) {
            switchMusic.setOnCheckedChangeListener(this);
        }

    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            ((TheGameApplication)getApplication()).mediaPlayer.start();
        } else {
            ((TheGameApplication)getApplication()).mediaPlayer.pause();
        }
    }
}
