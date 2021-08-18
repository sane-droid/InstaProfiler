package com.instaprofiler.app.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;
import com.instaprofiler.app.R;
import com.instaprofiler.app.data.model.User;
import com.instaprofiler.app.data.repository.ProfilerRepository;
import com.instaprofiler.app.ui.dialogfragment.ImageDialog;
import com.instaprofiler.app.ui.dialogfragment.ProgressDialog;

public class ProfileActivity extends AppCompatActivity {
    User instaUser;
    ImageButton profile_back_button;
    ImageView profilePhoto;
    ProfilerViewModel profilerViewModel;
    TextView textView, following, followers, name, bio, profileLink, posts;
    String user;
    ProgressDialog progressDialog;
    public static int MAX=4;
    public static final String LIMIT_PREF="COUNT_MAX_LIMIT";
    SharedPreferences pref=null;
    private int STORAGE_PERMISSION_REQUEST_CODE=202;
    private boolean storagePermissinGranted;

    public static class ProfilerViewModel extends ViewModel {
        public LiveData<ProfilerRepository.Result> liveData = null;
        ProfilerRepository profilerRepository = new ProfilerRepository();

        public void getAccountDetail(String userName) {
            liveData = profilerRepository.getAccountDetails(userName);
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
        pref=getSharedPreferences("COUNT_USE",MODE_PRIVATE);
        progressDialog=new ProgressDialog();
        Intent intent = getIntent();
        user = intent.getStringExtra("userName");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModelProvider.get(ProfilerViewModel.class);
        profilerViewModel = viewModelProvider.get(ProfilerViewModel.class);
        progressDialog.show(getSupportFragmentManager(),"profile_progress");
        profilerViewModel.getAccountDetail(user);

        profilePhoto.setOnClickListener(v -> {
            ImageDialog imageDialog = new ImageDialog(instaUser);
            imageDialog.show(getSupportFragmentManager(), "MY FRAGMENT");
        });

        profile_back_button.setOnClickListener(v -> {
            Intent intent1 = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent1);
        });

        profilerViewModel.liveData.observe(this, new Observer<ProfilerRepository.Result>() {
            @Override
            public void onChanged(ProfilerRepository.Result result) {


                User userProfile=result.user;
                String error=result.error;

                progressDialog.dismiss();
                if (userProfile!=null) {

                    try
                    {
                        textView.setText(user);
                        followers.setText( ImageDialog.getInMK(userProfile.getEdgeFollowedBy().getCount()) + "");
                        following.setText( ImageDialog.getInMK(userProfile.getEdgeFollow().getCount()) + "");
                        name.setText( userProfile.getFullName());
                        bio.setText( userProfile.getBiography());
                        posts.setText(  userProfile.getEdgeOwnerToTimelineMedia().getCount() + "");
                        Glide.with(ProfileActivity.this).
                                asBitmap().load(userProfile.getProfilePicUrlHd())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .circleCrop().into((ImageView) findViewById(R.id.profilePhoto));
                        profileLink.setText(  (String) userProfile.getExternalUrl());
                        if (userProfile.getIsPrivate()) {
                            final ImageButton button = findViewById(R.id.lockButton);
                            button.setVisibility(View.VISIBLE);
                        }
                        HomeActivity.decreaseLimit(pref);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Invaild username or connection problem",Toast.LENGTH_LONG).show();
                        finish();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                    finish();
                }
                instaUser = userProfile;
            }
        });
    }


}