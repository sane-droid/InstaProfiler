package com.instaprofiler.app.data.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.instaprofiler.app.data.api.InstagramApi;
import com.instaprofiler.app.data.model.InstaService;
import com.instaprofiler.app.data.model.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ProfilerRepository {
    public MutableLiveData<User> getAccountDetail(String userName) {
        MutableLiveData<User> liveData = new MutableLiveData<User>();
        new Thread(() -> {

            try {
                Document document = Jsoup.connect(InstagramApi.baseUrl + userName + "/channel" + InstagramApi.key)
                        .ignoreContentType(true).get();

                String body = document.body().toString();
                Log.d("Body", body);
                String json = body.replace("<body>", "").replace("</body>", "");

                JSONObject jsonObject = new JSONObject(json);
                Gson converter = new Gson();

                InstaService instaService = converter.fromJson(jsonObject.toString(), InstaService.class);
                User user = instaService.getGraphql().getUser();
                liveData.postValue(user);

            } catch (IOException e) {
                liveData.postValue(null);
                e.printStackTrace();
            } catch (JSONException e) {
                liveData.postValue(null);
                e.printStackTrace();
            }


        }).start();
        return liveData;
    }
}