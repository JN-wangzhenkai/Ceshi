package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pdd.ceshi.Utils.getUrlAns;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrlDopostActivity extends AppCompatActivity {

    private String urlString;
    private String ansString;
    private String netVersion;
    private OkHttpClient client;
    private String vdersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_dopost);

        findViewById(R.id.post_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
            }
        });
    }
     String result = null;

    private void test1() {
        String url="http://192.168.1.117:8080/miPush/miPushServlet?regid="+"0";
        client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        final Request request = builder.get().url(url).build();
        //3将Request封装成call
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                Log.d("111111", "sda: "+e.toString());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                Log.d("111111", "onResponse: "+response.body().string());
            }

        });
    }

    private void test() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.pddzn.com:8888/pdd/") //http://fy.iciba.com/
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        Api api = retrofit.create(Api.class);

        Call<ResponseBody> call = api.checkVersion();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("11111111", "onResponse: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }



    private void cesshi() {
        new Thread() {
            @Override
            public void run() {
                try {
                    ansString = getUrlAns.doPost(null, "http://www.pddzn.com:8888/pdd/version.action");
                    if (ansString != null) {
                        netVersion = ansString.substring(11, 17);//00002B
                        Log.d("11111111", "run: " + netVersion);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void pushregit(String regid){

        String url="192.168.1.117:8080/miPush/miPushServlet?regid="+regid;

        OkHttpClient client = new OkHttpClient();
//        Request.Builder builder = new Request.Builder();
//        final Request request = builder.get().url(url).build();

        Log.d("1111111", "pushregit: "+url);
        Request request = new Request.Builder()
                .get()
                .url("www.baidu.com")
                .build();

        client .newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d("111111", "onResponse: "+e.toString());

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.d("111111", "onResponse: "+response.body().string());

            }
        });

//        okhttp3.Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("111111", "onResponse: "+e.toString());
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                Log.d("111111", "onResponse: "+response.body().string());
//            }
//        });

    }



    private void getData(){

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.117:8080/userOperation/") //http://fy.iciba.com/
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        Api api = retrofit.create(Api.class);

        Call<ResponseBody> call = api.getData("111417032015");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("00000", "onResponse: "+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("000000", "onFailure: "+t.toString());
            }
        });
    }

}
