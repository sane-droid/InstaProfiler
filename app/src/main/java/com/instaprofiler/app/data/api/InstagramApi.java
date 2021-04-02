package com.instaprofiler.app.data.api;

public class InstagramApi {
    public static final String baseUrl = "https://www.instagram.com/";
    public static final String key = "/?__a=1";
//    public static InstagramProfile instagramProfile = null;
//
//    public static InstagramProfile getService() {
//        if (instagramProfile == null) {
//
//            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
//            httpClient.addInterceptor(loggingInterceptor);
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .client(httpClient.build())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            instagramProfile = retrofit.create(InstagramProfile.class);
//        }
//        return instagramProfile;
//    }
//
//    public interface InstagramProfile {
//        @GET ("{userName}/channel"+key)
//        Call<InstaService> getUser(@Path("userName") String baseUrl);
//    }
}
