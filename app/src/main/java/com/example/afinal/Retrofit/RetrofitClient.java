package com.example.afinal.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static  RetrofitClient instance=null;
    private Api api;
    private RetrofitClient(){
        Retrofit retro= new Retrofit.Builder().
                baseUrl(api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .build(); // b3ml el retro 3shan ygebly el data mn el api w bdelo el url w b3ml converter factory 3shan el extension
        api = retro.create(Api.class); // b3rf el api 3ala el retro 3shan y3rf hygeb eh
    }
    public static synchronized RetrofitClient getInstance(){
        if(instance==null){
            instance=new RetrofitClient();
        }
        return instance;
    }
    public Api getMyapi(){return api;}
}
