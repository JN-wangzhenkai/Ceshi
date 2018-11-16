package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pdd.ceshi.Utils.PmData;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private String url;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





String result="{\"resultcode\":\"200\",\"reason\":\"SUCCESSED!\",\"result\":[{\"city\":\"??\",\"PM2.5\":\"36\",\"AQI\":\"62\",\"quality\":\"?\",\"PM10\":\"65\",\"CO\":\"0.7\",\"NO2\":\"15\",\"O3\":\"164\",\"SO2\":\"9\",\"time\":\"2018-10-08 16:39:28\"}],\"error_code\":0}";

        Gson gson = new Gson();

        java.lang.reflect.Type type = new TypeToken<PmData>() {}.getType();

        PmData pmData = gson.fromJson(result, type);
        List<PmData.ResultBean>ss = pmData.getResult();

        Log.d("111111111", "onResponse: " + ss.get(0).get_$PM2568());





        url = "http://60.191.35.250:8083/oxygen-orderMgr-webServer/orderMgr/";


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        s = "http://192.168.1.117:8080/airquality/querypm?city=hangzhou";

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = "http://192.168.1.118:8080/pdd/check.action?mac=c12a7813f5d4&&sn=010115120801";

                final OkHttpClient client = new OkHttpClient();
                final Request request = new Request.Builder().url(s).build();
                client.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        Log.d("11111", "onFailure: " + e.toString());
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                        Log.d("11111", "success: " + response.body().string());

                        String result=response.body().string();
                        Gson gson = new Gson();

                        java.lang.reflect.Type type = new TypeToken<PmData>() {}.getType();

                        PmData pmData = gson.fromJson(result, type);
                        List<PmData.ResultBean>ss = pmData.getResult();

                        Log.d("111111111", "onResponse: " + ss.get(0).get_$PM2568());
                    }
                });


//                Api api= retrofit.create(Api.class);
//
//               Call<ResponseBody>call= api.orderQuery(1,1,10);
//               call.enqueue(new Callback<ResponseBody>() {
//                   @Override
//                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                       try {
//                           Log.d("111111111", "onResponse: "+response.body().string());
//                       } catch (IOException e) {
//                           e.printStackTrace();
//                       }
//                   }
//
//                   @Override
//                   public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                       Log.d("1111111", "onFailure: ");
//                   }
//               });
            }
        });
    }
}
