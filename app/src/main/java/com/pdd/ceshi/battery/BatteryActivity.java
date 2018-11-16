package com.pdd.ceshi.battery;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pdd.ceshi.R;

import java.util.Timer;
import java.util.TimerTask;

public class BatteryActivity extends AppCompatActivity {

    private BatteryView horizontalBattery, verticalBattery;
    private int power;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    horizontalBattery.setPower(power += 5);
                    if (power == 100) {
                        power = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        horizontalBattery = (BatteryView) findViewById(R.id.horizontalBattery);
        verticalBattery = (BatteryView) findViewById(R.id.verticalBattery);

        verticalBattery.setPower(85);
        horizontalBattery.setPower(30);



        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               // mHandler.sendEmptyMessage(0);
            }
        }, 0, 100);
    }


}
