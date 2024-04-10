package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.afinal.R;

public class OnBoardingScreen extends AppCompatActivity {
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Intent i= getIntent();
        b1=findViewById(R.id.GetStarted); //identify button on page for action
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(OnBoardingScreen.this, SingIn.class);
            startActivity(i);
            }
        });
    }
}