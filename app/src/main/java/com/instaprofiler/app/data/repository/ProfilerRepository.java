package com.instaprofiler.app.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.instaprofiler.app.data.model.UserProfile;

import java.io.IOException;

import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.cookie.CookieHashSet;
import me.postaddict.instagram.scraper.cookie.DefaultCookieJar;
import me.postaddict.instagram.scraper.interceptor.ErrorInterceptor;
import me.postaddict.instagram.scraper.model.Account;
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
        Account account = null;
        try {
            UserProfile userProfile = new UserProfile();
            userProfile.setUserId(account.getFullName());
            userProfile.setFollowers(account.getFollows().toString());
            userProfile.setFollowing(account.getFollowedBy().toString());
            userProfile.setBio(account.getBiography());
            liveData.postValue(userProfile);
            account = instagram.getAccountByUsername(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(account.getMedia().getCount());
        return liveData;
    }
}