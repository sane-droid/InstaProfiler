package com.instaprofiler.app.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.navigation.NavigationView;
import com.instaprofiler.app.R;
import com.instaprofiler.app.ui.dialogfragment.MessageDialog;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static com.instaprofiler.app.ui.activities.ProfileActivity.LIMIT_PREF;
import static com.instaprofiler.app.ui.activities.ProfileActivity.MAX;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG ="AD_EVENT" ;
    ImageButton home_search_button, hamburger;
    EditText userName;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RewardedAd mRewardedAd;
    SharedPreferences pref=null;
    TextView tokenTextView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_search_button = findViewById(R.id.home_search_button);
        userName = findViewById(R.id.home_insta_username);
        hamburger = findViewById(R.id.hamburger);
        navigationView = findViewById(R.id.navigationView);
        pref=getSharedPreferences("COUNT_USE",MODE_PRIVATE);
        tokenTextView=findViewById(R.id.tokens);

        dailyCheck(pref);
        showTokens();




        requestAd();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.about:
                        Intent intent_v=new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.sanedroid_url)));
                        startActivity(intent_v);
                        return true;
                    case  R.id.disclaimer:
                        new MessageDialog(getString(R.string.disclaimer_text)).show(getSupportFragmentManager(), "msg_disclaimer");
                        return true;
                }

                return true;
            }
        });
        home_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              int limit=pref.getInt(LIMIT_PREF,0);
                if (limit!=0 || mRewardedAd!=null) {
                    Activity activityContext = HomeActivity.this;

                    if(mRewardedAd!=null) {
                        mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                Log.d(TAG, "The user earned the reward.");
                                int rewardAmount = rewardItem.getAmount();
                                String rewardType = rewardItem.getType();
                                updateLimit(pref);
                                showTokens();
                            }

                        });
                    }
                    else if(limit!=0)
                    {

                        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                        String userId = userName.getText().toString();
                        intent.putExtra("userName", userId);
                        startActivity(intent);
                        showTokens();
                    }
                } else {

                    if(mRewardedAd==null) {
                        new MessageDialog("Limit exhausted \nYou have 0 tokens ,you will earn 4 tokens for one rewarded video or sorry if ads are not available,we add 4 tokens daily for you").show(getSupportFragmentManager(), "msg_details");
                    }
                    else
                    {

                    }
                    Toast.makeText(HomeActivity.this,"Try again later",Toast.LENGTH_LONG).show();
                    Log.d(TAG, "The rewarded ad wasn't ready yet.");
                    requestAd();
                }

            }
        });

        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout navDrawer = findViewById(R.id.drawerlayout);
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!navDrawer.isDrawerOpen(GravityCompat.START)) navDrawer.openDrawer(GravityCompat.START);
                else navDrawer.closeDrawer(GravityCompat.END);
            }
        });

        requestAd();


    }

    void requestAd()
    {
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, getString(R.string.admob_rewarded_id),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {


                        mRewardedAd = rewardedAd;
                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad was shown.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.d(TAG, "Ad failed to show.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d(TAG, "Ad was dismissed.");

                                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                                String userId = userName.getText().toString();
                                intent.putExtra("userName", userId);
                                startActivity(intent);
                                showTokens();
                                mRewardedAd = null;
                            }
                        });
                        Log.d(TAG, "Ad was loaded.");
                    }

                });
    }
    public static void updateLimit(SharedPreferences pref)
    {
        int current=pref.getInt(LIMIT_PREF,0);
        pref.edit().putInt(LIMIT_PREF,current+MAX).apply();
    }
    public static  void decreaseLimit(SharedPreferences pref)
    {
        int current=pref.getInt(LIMIT_PREF,0);
        if(current==0)
            return;
        pref.edit().putInt(LIMIT_PREF,current-1).apply();
    }

    private void showTokens() {
        if (tokenTextView != null)
        {
            int current=pref.getInt(LIMIT_PREF,0);
            tokenTextView.setText(current +" Tokens Remaining");
        }
    }
    public void  dailyCheck(SharedPreferences pref)
    {
        long last=pref.getLong("LAST_TIME",0L);
        long current= Calendar.getInstance().getTimeInMillis();
        int current_limit=pref.getInt(LIMIT_PREF,0);
        if(last==0 || TimeUnit.MILLISECONDS.toHours(current-last)>=24 && current_limit<=10)
        {
            updateLimit(pref);
            pref.edit().putLong("LAST_TIME",current).apply();
        }
    }

}