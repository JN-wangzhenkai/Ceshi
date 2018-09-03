package com.pdd.ceshi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetClient {

   // public static final String BASE_URL="http://192.168.1.117:8080/oxygen-userMgr-webServer/userMgr/";

//   public static final String BASE_URL = "http://admin.syfeicuiedu.com";
private String url="http://192.168.1.117:8080/oxygen-community-webServer/community/";

    private static NetClient sClient;

    private final OkHttpClient client;
    private final Retrofit retrofit;
    private final Gson gson;

    private NetClient() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        gson = new GsonBuilder().setLenient().create(); // 非严格模式

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                // 添加Converter (Gson转换器), 注意依赖
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public OkHttpClient getClient(){
        return client;
    }

    private UserApi userApi;

    public UserApi getUserApi() {
        if(userApi == null){
            userApi = retrofit.create(UserApi.class);
        }
        return userApi;
    }



    public static NetClient getInstance() {
        if (sClient == null) {
            sClient = new NetClient();
        }
        return sClient;
    }


}
