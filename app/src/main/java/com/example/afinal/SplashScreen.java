package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import com.example.afinal.R;
import com.example.afinal.Retrofit.RetrofitClient;
import com.example.afinal.data.fetchedData.Root;
import com.example.afinal.data.fetchedData.User;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
SplashScreen extends AppCompatActivity {
Handler delay; // to make splash screen appear for a while
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        /*Call <JSONObject> call= RetrofitClient.getInstance().getMyapi().updateuser("jera");
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                JSONObject hamada= response.body();
                try {
                    Log.d("hamada", (String) hamada.get("firstName"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        }); */
        delay = new Handler(); //create instance
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,OnBoardingScreen.class);

                startActivity(i);
            }
        },3000); //3seconds for splash screen
    }

}