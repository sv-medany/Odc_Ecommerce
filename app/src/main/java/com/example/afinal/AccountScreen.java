package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.afinal.databinding.ActivityAccountScreenBinding;
import com.example.afinal.databinding.ActivityFavoriteScreenBinding;

import java.util.logging.Logger;

public class AccountScreen extends AppCompatActivity {
    TextView adminName,adminMail;
    Button logout;
    ActivityAccountScreenBinding binding4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);
        adminName=findViewById(R.id.admin_name);
        adminMail=findViewById(R.id.admin_email);
        logout=findViewById(R.id.logoutbtn);
        adminName.setText(LogingScreen.globaluser.getFirstName() + LogingScreen.globaluser.getLastName());
        adminMail.setText(LogingScreen.globaluser.getEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountScreen.this,LogingScreen.class);
                startActivity(i);
            }
        });
        binding4= ActivityAccountScreenBinding.inflate(getLayoutInflater());
        binding4.nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case  R.id.shop:
                    Intent intent1=new Intent(AccountScreen.this,HomeScreen.class);
                    startActivity(intent1);
                    break;
                case R.id.exp:

                    Intent intent2=new Intent(AccountScreen.this,ExploreScreen.class);
                    startActivity(intent2);
                    break;
                case  R.id.cart:
                    Intent intent3 = new Intent(AccountScreen.this,MyCartScreen.class);
                    startActivity(intent3);
                    break;
                case  R.id.favv:
                    Intent intent4 = new Intent(AccountScreen.this,FavoriteScreen.class);
                    startActivity(intent4);
                    break;
                case  R.id.acc:
                    Intent intent5 = new Intent(AccountScreen.this,AccountScreen.class);
                    startActivity(intent5);
                    break;
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav,fragment);
        fragmentTransaction.commit();
    }
}
