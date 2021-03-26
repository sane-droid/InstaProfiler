package com.instaprofiler.app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.instaprofiler.app.R;
import com.instaprofiler.app.data.model.UserProfile;
import com.instaprofiler.app.data.repository.ProfilerRepository;
import com.instaprofiler.app.ui.viewmodel.ProfilerViewModel;

public class ProfileActivity extends AppCompatActivity {
    ImageButton profile_back_button;
    ProfilerViewModel profilerViewModel;
    TextView textView,following,followers,name,bio;
    String userId;

    public static class ProfilerViewModel extends ViewModel {
        public LiveData <UserProfile> liveData = null;
        ProfilerRepository profilerRepository = new ProfilerRepository();
        public void getAccountDetail(String userName){
            liveData = profilerRepository.getAccountDetail(userName);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile_back_button = findViewById(R.id.profile_back_button);
        textView = findViewById(R.id.profileUsername);
        following= findViewById(R.id.profileFollowing);
        followers = findViewById(R.id.profileFollowers);
        name = findViewById(R.id.profileName);
        bio = findViewById(R.id.profileBio);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userName");

        ViewModelProvider viewModelProvider = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModelProvider.get(ProfilerViewModel.class);
        profilerViewModel = viewModelProvider.get(ProfilerViewModel.class);
        profilerViewModel.getAccountDetail(userId);

        profilerViewModel.liveData.observe(this, new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile userProfile) {
                textView.setText(userId);
            }
        });
    }

}