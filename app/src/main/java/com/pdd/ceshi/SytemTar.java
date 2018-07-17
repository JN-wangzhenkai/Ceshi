package com.pdd.ceshi;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.pdd.ceshi.Utils.SystemBarTintManager;

public class SytemTar {

    private static SytemTar sytemTar;

    public static SytemTar getInstance() {

        if (sytemTar == null) {
            sytemTar = new SytemTar();

        }
        return sytemTar;
    }

    /**
     * Apply KitKat specific translucency.
     */
    public void applyKitKatTranslucency(Activity activity) {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true, activity);
            SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
            mTintManager.setStatusBarTintEnabled(true);

            mTintManager.setStatusBarTintResource(R.color.colorAccent);//通知栏所需颜色
        }

    }


    /**
     * 透明色
     * Apply KitKat specific translucency.
     */
    public void applyKitKatTranslucencyTran(Activity activity) {

        // KitKat translucent navigation/status bar.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true, activity);
            SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
            mTintManager.setStatusBarTintEnabled(true);

            mTintManager.setStatusBarTintResource(R.color.colorAccent);//通知栏所需颜色
        }

    }

    private void setTranslucentStatus(boolean on, Activity activity) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}