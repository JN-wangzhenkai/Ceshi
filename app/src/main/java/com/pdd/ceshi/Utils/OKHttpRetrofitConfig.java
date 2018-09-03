package com.pdd.ceshi.Utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OKHttpRetrofitConfig {

    public <T> T setCofing(String baseUrl, Class<T> t) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000 * 5, TimeUnit.SECONDS)
                // .addInterceptor(new HttpNetWorkInterceptor())
               // .addInterceptor(new OkhttpIntercepter())
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//设置支持gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// rxjava
                .client(okHttpClient)
                .build();

        return mRetrofit.create(t);
    }



}
