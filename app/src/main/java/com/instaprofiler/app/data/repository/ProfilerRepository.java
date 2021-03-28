package com.instaprofiler.app.data.repository;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.instaprofiler.app.data.model.UserProfile;

import java.io.IOException;

import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.cookie.CookieHashSet;
import me.postaddict.instagram.scraper.cookie.DefaultCookieJar;
import me.postaddict.instagram.scraper.domain.Account;
import me.postaddict.instagram.scraper.interceptor.ErrorInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class ProfilerRepository{
    //TODO implement logic calling java scrapper app
    public MutableLiveData<UserProfile> getAccountDetail(String userName){
        MutableLiveData <UserProfile> liveData = new MutableLiveData<UserProfile>();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(new ErrorInterceptor())
                .cookieJar(new DefaultCookieJar(new CookieHashSet()))
                .build();

        Instagram instagram = new Instagram(httpClient);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Account account = null;
                try {
                    account = instagram.getAccountByUsername(userName);
                    UserProfile userProfile = new UserProfile();
                    userProfile.setUserId(account.id+"");
                    userProfile.setFollowers(account.followsCount+"");
                    userProfile.setFollowing(account.followedByCount+"");
                    userProfile.setBio(account.biography);
                    liveData.postValue(userProfile);
                } catch (IOException e) {
                    Log.d("Err",e.getLocalizedMessage());
                }
            }
        }).start();

        return liveData;
    }
}