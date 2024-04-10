package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.Helpers.CartHelper;
import com.example.afinal.Helpers.FavHelper;
import com.example.afinal.Helpers.Helper;
import com.example.afinal.data.fetchedData.Root;
import com.example.afinal.data.fetchedData.User;
import com.example.afinal.viewModels.UsersViewModel;
import com.facebook.internal.Validate;

public class LogingScreen extends AppCompatActivity {
    EditText mail, pass;
    Button btnlog;
    TextView mailText, passText, sign;
    UsersViewModel usersViewModel;
    Root root;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static final String shared = "sharedprefs";
    public static CartHelper dbHelper;
    public static FavHelper favHelper;
    public static final String name="name";
    public static final String emailuser="email";
    public static final String id="id";
    public static User globaluser;
    boolean flag = false;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging_screen);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        btnlog = findViewById(R.id.btnlog);
        mailText = findViewById(R.id.mailText);
        passText = findViewById(R.id.passText);
        sign = findViewById(R.id.sign);
        sharedPreferences = getSharedPreferences(shared, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String dir = sharedPreferences.getString("state", "false");
        dbHelper = new CartHelper(LogingScreen.this);
        favHelper = new FavHelper(LogingScreen.this);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogingScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });
        usersViewModel = new ViewModelProvider(LogingScreen.this).get(UsersViewModel.class);
        dialog = new ProgressDialog(LogingScreen.this);
        dialog.setTitle("fetching");
        dialog.setMessage("Loading data");
        dialog.setCancelable(false);
        usersViewModel.getUsers(LogingScreen.this).observe(LogingScreen.this, users -> {
            root = users;
            dialog.cancel();


        });
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dir.equals("false")) {
                    Validate();
                } else {
                    Intent intent = new Intent(LogingScreen.this, HomeScreen.class);
                    Helper.setuser();
                    //Helper.retrieveordrer();

                    startActivity(intent);
                }
            }
        });

        }


        public void Validate() {
            String email = mail.getText().toString();
            String passs = pass.getText().toString();
            if (email.isEmpty() || passs.isEmpty()) {
                Toast.makeText(this, "Please fill up all your data", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < root.getUsers().size(); i++) {
                    String verMail = root.getUsers().get(i).getEmail();
                    String verPass = root.getUsers().get(i).getPassword();
                    if (verMail.equals(email) && verPass.equals(passs)) {
                        flag = true;
                        User user = root.getUsers().get(i);
                        globaluser = user;
                        break;
                        // Intent intent= new Intent(LogingScreen.this,HomeScreen.class);
                        // startActivity(intent);
                    }
                }
                if (flag == true) {
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogingScreen.this, HomeScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Sorry ! Incorrect Data !", Toast.LENGTH_SHORT).show();
                    mail.setText("");
                    pass.setText("");
                    mailText.setTextColor(Color.parseColor("#F60808"));
                    passText.setTextColor(Color.parseColor("#F60808"));
                }

            }
        }
    }

