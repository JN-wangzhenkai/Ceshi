package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        url = "http://60.191.35.250:8083/oxygen-orderMgr-webServer/orderMgr/";


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api api= retrofit.create(Api.class);

               Call<ResponseBody>call= api.orderQuery(1,1,10);
               call.enqueue(new Callback<ResponseBody>() {
                   @Override
                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       try {
                           Log.d("111111111", "onResponse: "+response.body().string());
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }

                   @Override
                   public void onFailure(Call<ResponseBody> call, Throwable t) {

                       Log.d("1111111", "onFailure: ");
                   }
               });
            }
        });
    }
}
