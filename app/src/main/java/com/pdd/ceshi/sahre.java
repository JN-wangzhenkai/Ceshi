package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sahre extends AppCompatActivity {

    private CheckBox mcheck;
    private Button button;

    private OkHttpClient client;

    String url="http://60.191.35.250:8083/oxygen-orderMgr-webServer/orderMgr/";
    private Retrofit mretrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahre);

        mcheck=findViewById(R.id.check);

        Log.d("111111", "onCreate: "+mcheck.isChecked());

        button=findViewById(R.id.share_btn);


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url) //http://fy.iciba.com/
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api api= retrofit.create(Api.class);

                Call<ResponseBody> call = api.orderCreate(45, "0123456789", "hzpddylypyxgs", 94,1);


                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("111111111111", "onResponse: "+response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Log.d("111111111", "onFailure: ");
                    }
                });
            }
        });
    }
}
