package com.example.afinal.viewModels;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.afinal.Retrofit.RetrofitClient;
import com.example.afinal.data.fetchedData.Root;
import com.google.android.gms.common.api.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class UsersViewModel extends ViewModel {
    private MutableLiveData<Root> userslist;
    ProgressDialog dialog;

    public MutableLiveData<Root> getUsers(Context ctx) {

        if (userslist == null) {
            userslist = new MutableLiveData<>();
            loadUsers(ctx);
        }
        return userslist;
    }
    public ProgressDialog progressDialog(){
        ProgressDialog dialog = new ProgressDialog(progressDialog().getOwnerActivity());
        dialog.setTitle("API Fetching");
        dialog.setMessage("Loading Data......");
        dialog.setCancelable(false);
        return dialog;
    }
    private void loadUsers(Context ctx){

        Call<Root> call = RetrofitClient.getInstance().getMyapi().getUsers();
        call.enqueue(new Callback<Root>() {

            @Override
            public void onResponse(Call<Root> call, retrofit2.Response<Root> response) {
                if(!response.isSuccessful()){

                }else {
                    userslist.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });

    }
}
