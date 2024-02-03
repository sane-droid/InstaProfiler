package com.instaprofiler.app.data.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.Gson;
import com.instaprofiler.app.data.api.InstagramApi;
import com.instaprofiler.app.data.model.InstaService;
import com.instaprofiler.app.data.model.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ProfilerRepository {

    public class Result
    {
        public User user;
        public String error=null;
        Result(User user,String error)
        {
            this.user=user;
            this.error=error;
        }
    }
    public MutableLiveData<Result> getAccountDetail(String userName) {
        MutableLiveData<Result> liveData = new MutableLiveData<>();
        new Thread(() -> {

            try {
                String er="";
                Document document = Jsoup.connect(InstagramApi.baseUrl + userName + "/channel" + InstagramApi.key)
                        .userAgent("Mozilla").ignoreContentType(true).get();
                String body = document.body().toString();
                Log.d("Body", body);
                String json = body.replace("<body>", "").replace("</body>", "");

                JSONObject jsonObject = new JSONObject(json);
                Gson converter = new Gson();

                InstaService instaService = converter.fromJson(jsonObject.toString(), InstaService.class);
                User user = instaService.getGraphql().getUser();
                liveData.postValue(new Result(user,""));

            } catch (IOException e) {
                liveData.postValue(new Result(null,e.getLocalizedMessage()));
                e.printStackTrace();
            } catch (JSONException e) {
                liveData.postValue(new Result(null,e.getLocalizedMessage()));
                e.printStackTrace();
            }
            catch (Exception e)
            {
                liveData.postValue(new Result(null,e.getLocalizedMessage()));
                e.printStackTrace();
            }


        }).start();
        return liveData;
    }

    public MutableLiveData<Result> getAccountDetails(String user)
    {
        MutableLiveData<Result> liveData = new MutableLiveData<>();
        InstagramApi.getService().getUser(user).enqueue(new Callback<InstaService>() {
            @Override
            public void onResponse(Call<InstaService> call, Response<InstaService> response) {

                if (response.isSuccessful())
                {
                    liveData.postValue(new Result(response.body().getGraphql().getUser(),""));
                    Log.d("response",response.body().getGraphql().getUser().getFullName());
                }
                else
                {
                    liveData.postValue(new Result(null,response.errorBody().toString()));
                    Log.e("error",response.raw().toString());
                }

            }

            @Override
            public void onFailure(Call<InstaService> call, Throwable t) {

                liveData.postValue(new Result(null,"Connection error try after some time"));
                Log.e("error t",t.getLocalizedMessage());
            }
        });
return liveData;
    }

}