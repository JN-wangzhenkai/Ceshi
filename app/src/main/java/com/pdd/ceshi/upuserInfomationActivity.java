package com.pdd.ceshi;

import android.graphics.drawable.ClipDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Action1;

public class upuserInfomationActivity extends AppCompatActivity {


    private OkHttpClient client;
    private ImageView mImageview;
    private Api userApi;
    private JsonObject jsonObject1;
    //数据上传服务器
    Subscription subscription;
    private JsonObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upuser_infomation);
        mImageview = findViewById(R.id.imageview);
        ClipDrawable drawable = (ClipDrawable) mImageview.getDrawable();
        drawable.setLevel(3000);


        String url1 = "http://www.pddzn.com:8888/";
        String url2 = "http://192.168.1.117:8080/test/";

        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url2)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        userApi = retrofit.create(Api.class);


        JsonArray jsonArray = new JsonArray();

        jsonObject = new JsonObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = format.format(new Date());

        jsonObject.addProperty("htpv", "80");
        jsonObject.addProperty("hxplString", "20");
        jsonObject.addProperty("press", "1016");
        jsonObject.addProperty("mos", "56");
        jsonObject.addProperty("temp", "25");
        jsonObject.addProperty("tim", time);
        jsonObject.addProperty("mac","81971ff37b98");

        jsonArray.add(jsonObject);

        jsonObject1 = new JsonObject();
        jsonObject1.add("rows", jsonArray);


        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updelay();

                Log.d("0000000", "onClick: "+jsonObject);
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Log.e("TAG","没隔10秒执行一次操作");



                    }
                },1000,1000);            }
        });
    }

    private void updelay() {
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                        Log.d("00000", "onNext: 1111");

                    }

                    @Override
                    public void onNext(Long aLong) {

                        Log.d("00000", "onNext: 1231");
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("00000", "onNext: 2222" + e.toString());

                    }

                    @Override
                    public void onComplete() {

                        Log.d("00000", "onNext: 3333");

                    }
                });
    }


    private void up1() {

        Call<ResponseBody> call=userApi.upToServeByPost2( jsonObject.toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if(null!=response.body()){
                    try {
                        Log.d("000000000", "onResponse: "+response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("000000000", "fail: "+t.toString());

            }
        });
//        userApi.upToServeByPost2("81971ff37b98", jsonObject1.toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResponseBody>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                        Log.d("00000000", "onSubscribe: " + d);
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//
//                        try {
//                            Log.d("00000000", "onSubscribe: " + responseBody.string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        Log.d("00000000", "onError: " + e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }


    private static Disposable mDisposable;

    /**
     * 取消订阅
     */
    public static void cancel() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public interface IRxNext {
        void doNext(long number);
    }

    @Override
    protected void onDestroy() {
        //取消定时器
        cancel();
        super.onDestroy();
    }

}
