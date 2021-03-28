package com.instaprofiler.app.ui.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.instaprofiler.app.data.model.UserProfile;
import com.instaprofiler.app.data.repository.ProfilerRepository;

public class ProfilerViewModel extends ViewModel {
    public LiveData <UserProfile> liveData = new MutableLiveData();
    ProfilerRepository profilerRepository = new ProfilerRepository();
    public void getAccountDetail(String userName){

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                liveData = profilerRepository.getAccountDetail(userName);
            }
        });

    }
}

