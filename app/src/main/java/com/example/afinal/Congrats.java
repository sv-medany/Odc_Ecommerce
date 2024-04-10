package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Congrats extends AppCompatActivity {
   Button track;
   TextView homee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        getIntent();
        track=findViewById(R.id.track_order);
        homee=findViewById(R.id.homee);
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Congrats.this,HomeScreen.class);
                startActivity(i);
            }
        });
        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(Congrats.this,HomeScreen.class);
                startActivity(ii);
            }
        });
    }
}