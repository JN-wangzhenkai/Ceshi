package com.pdd.ceshi;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pdd.ceshi.Utils.PermissionRequestUtil;


public class permissionActivity extends AppCompatActivity implements PermissionRequestUtil.PermissionRequestListener{


    private final String TAG = this.getClass().getName();
    private static final int myCode = 0x11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);


        //动态申请权限(动态申请的权限需要在AndroidManifest.xml中声明)
        PermissionRequestUtil.judgePermissionOver23(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                myCode);


    }


    @Override
    public void onPermissionReqResult(int reqCode, boolean isAllow) {
        if (reqCode != myCode) {
            return;
        }
        if (isAllow) {
            //被授权
            Toast.makeText(this, "已获取所有权限", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "已获取所有权限");
        } else {
            //被拒绝
            Toast.makeText(this,
                    "App申请的权限已被拒绝,为了能正常使用,请进入设置--权限管理打开相关权限", Toast.LENGTH_LONG).show();
            Log.d(TAG, "App申请的权限已被拒绝,为了能正常使用,请进入设置--权限管理打开相关权限");
        }

    }


    /**
     * 重写这个系统方法
     *
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 请求结果数据集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //调用封装好的方法
        PermissionRequestUtil.solvePermissionRequest(this, requestCode, grantResults);
    }

}