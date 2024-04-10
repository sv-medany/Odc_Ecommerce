package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.afinal.data.fetchedData.Root;

public class SignUpScreen extends AppCompatActivity {
    EditText userSign,mailSign,passSign;
    Button signbtn;
    Root root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        userSign=findViewById(R.id.userSign);
        mailSign=findViewById(R.id.mailSign);
        passSign=findViewById(R.id.passSign);
        signbtn=findViewById(R.id.signbtn);
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mailSign.getText().toString().isEmpty()||userSign.getText().toString().isEmpty()||passSign.getText().toString().isEmpty()){
                    Toast.makeText(SignUpScreen.this, "Please fill in all your data!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ValidateSignUp();
                }
            }
        });
    }

    private void ValidateSignUp() {
        String mailText= mailSign.getText().toString();
        for (int i=0; i<root.getUsers().size();i++){
            String verMail = root.getUsers().get(i).getEmail();
            if(verMail.equals(mailText)){
                Toast.makeText(this, "It seems like you already have an account with this mail", Toast.LENGTH_SHORT).show();
            }
        }

    }
}