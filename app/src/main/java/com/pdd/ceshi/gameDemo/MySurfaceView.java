package com.pdd.ceshi.gameDemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;

import java.util.Date;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    public static float g=198f;//模拟重力加速度
    private SurfaceHolder sfh;
    private Thread thread;
    private Canvas canvas;
    private Paint paint;
    private Date startTime;
    private int ScreenW, ScreenH;
    private Boolean tag=false;
    private float y=100;
    private float v=50f;
    public MySurfaceView(Context context) {
        super(context);
        thread=new Thread(this);
        sfh=this.getHolder();
        // callback接口:只要继承SurfaceView类并实现SurfaceHolder.Callback接口就可以实现一个自定义的SurfaceView了，
        // SurfaceHolder.Callback在底层的Surface状态发生变化的时候通知View.
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        this.setKeepScreenOn(true);// 保持屏幕常亮
//        if(canvas != null) {
//            RectF rectF = new RectF(0, 0, this.getWidth(), this.getHeight());
//            canvas.drawRect(rectF,paint);
//            sfh.unlockCanvasAndPost(canvas);
//        }
    }
    //重力公式y=1/2gt^2 g=9.8 t是时间
    private void draw() {
        try {
            canvas = sfh.lockCanvas(); // 得到一个canvas实例
            canvas.drawColor(Color.WHITE);// 刷屏
            canvas.drawCircle(100,y,100,paint);
            Date nowTime=new Date(System.currentTimeMillis());//可以获取当前时间

//            if(!tag){
//                v=v+TimeUtil.shijiancha(nowTime,startTime)*g;
//                y=y+v*TimeUtil.shijiancha(nowTime,startTime);
//            }else{
//                v=v-TimeUtil.shijiancha(nowTime,startTime)*g;
//                y=y-v*TimeUtil.shijiancha(nowTime,startTime);
//            }
            startTime=new Date(System.currentTimeMillis());//可以获取当前时间
        } catch (Exception ex) {
        } finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);  // 将画好的画布提交
        }
    }
    @Override
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ScreenW = this.getWidth();
        ScreenH = this.getHeight();
        startTime=new Date(System.currentTimeMillis());//可以获取当前时间
//        this.draw();
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        while (true) {
            draw();
            if(y>=ScreenH){
                tag=true;
                v=v-50;
            }
            if(v<=0){//是否改变运行轨迹要以速度大小为标准，我之前层判断y数值大小是不对的.
                tag=false;
            }

//            try {
////                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
    }
}

