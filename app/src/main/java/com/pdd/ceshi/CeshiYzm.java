package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CeshiYzm extends AppCompatActivity {

    private OkHttpClient client;
    private Button mBtn;

    String url="http://192.168.1.117:8080/oxygen-userMgr-webServer/userMgr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceshi_yzm);

        mBtn=findViewById(R.id.ceshi);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url) //http://fy.iciba.com/
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api api= retrofit.create(Api.class);
               Call<ResponseBody>call= api.yzm(add("13989891987","5935"));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("1111111", "onResponse: "+response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Log.d("11111111", "onFailure: "+call.toString()  +t.toString());
                    }
                });
            }
        });
    }


    //拼接字符串

    private String addCommaStr(List<String> list) {
      //  ”appkey=xxxx&amp;phone=xxx&amp;zone=86&amp;&amp;code=xx”

        String str = "";
        StringBuffer stringBuffer = new StringBuffer();

        if (list.size() < 2) {
            str = stringBuffer.append(list.get(0)).toString();

        } else {
            for (int i = 0; i < list.size(); i++) {

                stringBuffer.append(list.get(i) + ",");

            }
            str = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1).trim();
        }


        return str;
    }

    private String add(String phone,String code){
        String str = "";
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("appkey=266285f12375c&amp;phone="+phone+"&amp;zone=86&amp;&amp;code="+code);
        str=stringBuffer.toString();

        Log.d("1111111111", "add: "+str);
        return str;
    }
}
