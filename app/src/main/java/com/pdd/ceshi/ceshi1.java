package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ceshi1 extends AppCompatActivity {

    private Button button;
    private OkHttpClient client;

    String url="http://192.168.1.117:8080/messagePush/";
    private Retrofit mretrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceshi1);
        button=findViewById(R.id.button);

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

                BigDecimal bigDecimal = new BigDecimal("0.01");
                int totalPrice = bigDecimal.multiply(new BigDecimal(100)).intValue();

                Log.d("11111111111", "onClick: ----------"+totalPrice);


               Api api= retrofit.create(Api.class);

              Call<ResponseBody> call=api.getwuliu("YD","3876861612692");

              call.enqueue(new Callback<ResponseBody>() {
                  @Override
                  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

//                      Log.d("111111", "onResponse: "+response);
                      try {
                          Log.d("1111111", "onResponse: "+response.body().string());
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  }

                  @Override
                  public void onFailure(Call<ResponseBody> call, Throwable t) {

                      Log.d("111111", "onFailure: "+call.toString()+t.toString());
                  }
              });

            }
        });
    }
}
