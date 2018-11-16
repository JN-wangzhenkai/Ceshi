package com.pdd.ceshi.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pdd.ceshi.R;

public class CountDownActivity extends AppCompatActivity {


    private CountdownTextView countdownTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        countdownTextView = findViewById(R.id.down);
        countdownTextView.init("剩余%s", 24);
        countdownTextView.start(0);

        Log.d("4444444", "onCreate: " + countdownTextView.mSeconds);


        // 注册本地广播(当登陆注册成功后入口页面进行关闭操作)
        IntentFilter intentFilter = new IntentFilter(CountdownTextView.END);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);


        countdownTextView.removeOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

            }
        });


        countdownTextView.removeOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

                Log.d("4444444", "Attach: ");
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Log.d("4444444", "Detach: ");

            }
        });
    }


    // 广播接收器(当登陆注册成功后入口页面进行关闭操作)
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("4444444", "onReceive: 完蛋啦哈哈！！！！");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 取消
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
