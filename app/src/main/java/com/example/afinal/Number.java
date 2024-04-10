package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.afinal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hbb20.CountryCodePicker;

public class Number extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText e1;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_phone);

        Intent i=getIntent();
        ccp=findViewById(R.id.ccp2);
        e1=findViewById(R.id.phonedata);
        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneno="+"+ccp.getSelectedCountryCode()+e1.getText().toString();
                Intent ii = new Intent(Number.this,Verification.class);
                ii.putExtra("phone",phoneno);
                startActivity(ii);
            }
        });

    }
}