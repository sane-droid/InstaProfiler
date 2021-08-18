package com.instaprofiler.app.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.instaprofiler.app.data.model.User;
import com.instaprofiler.app.data.repository.ProfilerRepository;

import javax.xml.transform.Result;

public class ProfilerViewModel extends ViewModel {
    public LiveData <ProfilerRepository.Result> liveData = null;
    ProfilerRepository profilerRepository = new ProfilerRepository();

    public void getAccountDetail(String userName){
        liveData = profilerRepository.getAccountDetails(userName);
    }
}

