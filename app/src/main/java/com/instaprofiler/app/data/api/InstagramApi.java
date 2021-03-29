package com.instaprofiler.app.data.api;

import com.instaprofiler.app.data.model.InstaService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
            httpClient.addInterceptor(loggingInterceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
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
