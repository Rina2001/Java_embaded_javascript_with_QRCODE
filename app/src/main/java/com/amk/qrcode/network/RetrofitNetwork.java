package com.amk.qrcode.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rina on 4/26/17.
 */

public class RetrofitNetwork {


   static String API_BASE_URL = "https://api.github.com/";

   static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

   static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    static Retrofit getRetrofit(){
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit;
    }

}
