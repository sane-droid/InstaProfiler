package com.instaprofiler.app.data.api;

import com.instaprofiler.app.data.model.InstaService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class InstagramApi {
    public static final String baseUrl = "https://www.instagram.com/";
    public static final String key = "/?__a=1";
    public static InstagramProfile instagramProfile = null;

    public static InstagramProfile getService() {
        if (instagramProfile == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instagramProfile = retrofit.create(InstagramProfile.class);
        }
        return instagramProfile;
    }

    public interface InstagramProfile {
        @GET ("{userName}/channel"+key)
        Call<InstaService> getUser(@Path("userName") String baseUrl);
    }
}
