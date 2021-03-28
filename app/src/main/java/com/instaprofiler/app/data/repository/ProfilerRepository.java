package com.instaprofiler.app.data.repository;


import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.instaprofiler.app.data.model.InstaService;
import com.instaprofiler.app.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.instaprofiler.app.data.api.InstagramApi.getService;

public class ProfilerRepository{
    //TODO implement logic calling java scrapper app
    public MutableLiveData<User> getAccountDetail (String userName){
        MutableLiveData <User> liveData = new MutableLiveData<User>();
        getService().getUser(userName).enqueue(new Callback<InstaService>() {
            @Override
            public void onResponse(Call<InstaService> call, Response<InstaService> response) {
                User userId = response.body().getGraphql().getUser();
                liveData.postValue(userId);
            }

            @Override
            public void onFailure(Call<InstaService> call, Throwable t) {
                liveData.postValue(null);
                t.getMessage();
            }
        });
        return liveData;
    }
}