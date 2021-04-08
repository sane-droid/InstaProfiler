package com.instaprofiler.app.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.instaprofiler.app.R;
import com.instaprofiler.app.data.model.User;
import com.instaprofiler.app.data.repository.ProfilerRepository;
import com.instaprofiler.app.ui.dialogfragment.ImageDialog;

public class ProfileActivity extends AppCompatActivity {
    User instaUser;
    ImageButton profile_back_button;
    ImageView profilePhoto;
    ProfilerViewModel profilerViewModel;
    TextView textView, following, followers, name, bio, profileLink, posts;
    String user;

    public static class ProfilerViewModel extends ViewModel {
        public LiveData<User> liveData = null;
        ProfilerRepository profilerRepository = new ProfilerRepository();

        public void getAccountDetail(String userName) {
            liveData = profilerRepository.getAccountDetail(userName);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile_back_button = findViewById(R.id.profile_back_button);
        textView = findViewById(R.id.profileUsername);
        following = findViewById(R.id.profileFollowing);
        followers = findViewById(R.id.profileFollowers);
        name = findViewById(R.id.profileName);
        bio = findViewById(R.id.profileBio);
        profileLink = findViewById(R.id.profileLink);
        profilePhoto = findViewById(R.id.profilePhoto);
        posts = findViewById(R.id.posts);
        Intent intent = getIntent();
        user = intent.getStringExtra("userName");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModelProvider.get(ProfilerViewModel.class);
        profilerViewModel = viewModelProvider.get(ProfilerViewModel.class);
        profilerViewModel.getAccountDetail(user);

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDialog imageDialog = new ImageDialog(instaUser.getProfilePicUrlHd());
                imageDialog.show(getSupportFragmentManager(), "MY FRAGMENT");
            }
        });

        profile_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent1);
            }
        });

        profilerViewModel.liveData.observe(this, new Observer<User>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(User userProfile) {
                textView.setText(user);
                if (user != null) {
                    followers.setText("Followers\n" + userProfile.getEdgeFollowedBy().getCount() + "");
                    following.setText("Following\n" + userProfile.getEdgeFollow().getCount() + "");
                    name.setText("Name\n" + userProfile.getFullName());
                    bio.setText("Bio\n" + userProfile.getBiography());
                    posts.setText("POSTS\n" + userProfile.getEdgeSavedMedia().getCount() + "");
                    Glide.with(ProfileActivity.this).
                            asBitmap().load(userProfile.getProfilePicUrlHd())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .circleCrop().into((ImageView) findViewById(R.id.profilePhoto));
                    profileLink.setText("Link\n" + (String) userProfile.getExternalUrl());
                    if (userProfile.getIsPrivate()) {
                        final ImageButton button = findViewById(R.id.lockButton);
                        button.setVisibility(View.VISIBLE);
                    }
                    instaUser = userProfile;
                }
            }
        });
    }

}