package com.instaprofiler.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.instaprofiler.app.R;
import com.instaprofiler.app.data.model.User;
import com.instaprofiler.app.data.repository.ProfilerRepository;

public class ProfileActivity extends AppCompatActivity {
    ImageButton profile_back_button;
    ProfilerViewModel profilerViewModel;
    TextView textView,following,followers,name,bio;
    String user;

    public static class ProfilerViewModel extends ViewModel {
        public LiveData <User> liveData = null;
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
        user = intent.getStringExtra("userName");

        ViewModelProvider viewModelProvider = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModelProvider.get(ProfilerViewModel.class);
        profilerViewModel = viewModelProvider.get(ProfilerViewModel.class);
        profilerViewModel.getAccountDetail(user);

        profilerViewModel.liveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User userProfile) {
                textView.setText(user);
                followers.setText(userProfile.getEdgeFollowedBy().getCount());
                following.setText(userProfile.getEdgeFollow().getCount());
                name.setText(userProfile.getFullName());
                bio.setText(userProfile.getBiography());
                Glide.with(ProfileActivity.this).load(userProfile.getProfilePicUrlHd()).circleCrop().into((ImageView) findViewById(R.id.profilePhoto));
            }
        });
    }

}