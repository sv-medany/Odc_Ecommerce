package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    TextView name,mail;
    Button logout, cont;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        logout=findViewById(R.id.logout);
        cont=findViewById(R.id.cont);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Profile.this,HomeScreen.class);
                startActivity(intent);
            }
        });

        GoogleSignInAccount googleSignInAccount= GoogleSignIn.getLastSignedInAccount(this);
        if(googleSignInAccount!=null){
            name.setText(googleSignInAccount.getDisplayName());
            mail.setText(googleSignInAccount.getEmail());
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("Name;");
                mail.setText("Mail");
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Profile.this,SplashScreen.class);
                startActivity(i);
            }
        });
    }

}