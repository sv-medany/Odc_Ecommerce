package com.example.afinal.Retrofit;

import com.example.afinal.data.fetchedData.Products;
import com.example.afinal.data.fetchedData.Root;
import com.example.afinal.data.fetchedData.User;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL="https://dummyjson.com/";
    @GET ("users")
    Call <Root> getUsers();

    @FormUrlEncoded
    @POST("add")
    Call<JSONObject> updateuser(
        @Field("firstName") String firstname
    );
    @GET("products?limit=30&skip=10")
    Call <Products> getexculusiveoffers();
    @GET("products?limit=10&skip=50")
    Call <Products> getbestselling();
    @GET("products?limit=10&skip=30")
    Call <Products> getnewarrivals();
    @GET("products/categories")
    Call <List<String>> getCategories();
    @GET("products/category/{shaker}")
    Call <Products> getbyCategory(@Path("shaker") String shaker);

}
