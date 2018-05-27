package com.example.ilysh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button buttonSettings;
    private TextView textViewPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int points;
        int defaultValue = 0;
        Intent intent = getIntent();
        points = intent.getIntExtra("myIntVariableName", defaultValue);


        buttonStart=(Button)findViewById(R.id.buttonStart);
        buttonSettings=(Button)findViewById(R.id.buttonSettings);
        textViewPoint=(TextView)findViewById(R.id.textViewPoints);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, TheGame.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
