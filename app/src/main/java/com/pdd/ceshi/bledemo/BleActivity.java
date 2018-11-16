package com.pdd.ceshi.bledemo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pdd.ceshi.R;

public class BleActivity extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble2);


        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {// API<=17时
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) { // api>17, 蓝牙适配器必须在 API4.3 以上
            final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBluetoothAdapter = bluetoothManager.getAdapter();
        }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scandle(true);
            }
        });
    }

    private void scandle(boolean enable) {

        if (enable) {
            Thread thread = new Thread() {
                public void run() {
                    if (mBluetoothAdapter != null) {
                        mBluetoothAdapter.startLeScan(leScanCallback);
                    }
                    try {
                        this.sleep(100000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mBluetoothAdapter.stopLeScan(leScanCallback);

                }
            };
            thread.start();
        }

    }

    //设备扫描回调
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

            Log.d("1111111111", "onLeScan: " + device.getAddress() + "------" + device.getName());
        }
    };
}
