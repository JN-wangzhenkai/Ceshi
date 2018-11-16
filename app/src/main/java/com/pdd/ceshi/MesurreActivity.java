package com.pdd.ceshi;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pdd.ceshi.Utils.MeasureUtil;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MesurreActivity extends AppCompatActivity implements Animation.AnimationListener {


    private Animation trans;
    int index = 1;
    private float screenWidth;
    private float screenHeight;
    private ImageView mImageview;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesurre);

        width = MeasureUtil.getScreenSize(MesurreActivity.this)[0];
        height = MeasureUtil.getScreenSize(MesurreActivity.this)[1];
        mImageview=findViewById(R.id.img);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();


        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //mcontext是一个Context类型的参数

                draw();
            }
        });
    }

    private void draw() {

        trans = AnimationUtils.loadAnimation(MesurreActivity.this, R.anim.trans);
        mImageview.startAnimation(trans);
        trans.setAnimationListener(MesurreActivity.this);

    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onAnimationEnd(Animation animation) {

        switch (index) {
            case 1:
                index++;
                animation = AnimationUtils.loadAnimation(this, R.anim.trans2);
                mImageview.startAnimation(animation);
                animation.setAnimationListener(this);
                break;
            case 2:

                index++;
                animation = AnimationUtils.loadAnimation(this, R.anim.trans3);
                mImageview.startAnimation(animation);
                animation.setAnimationListener(this);
                break;


            case 3:
            {
                index++;
                animation = AnimationUtils.loadAnimation(this, R.anim.trans4);
                mImageview.startAnimation(animation);
                animation.setAnimationListener(this);

            }
            break;

            case 4://第三个动画
            {
                index++;
                animation = AnimationUtils.loadAnimation(this, R.anim.trans5);
                mImageview.startAnimation(animation);
                animation.setAnimationListener(this);
            }
            break;

            case 5://第三个动画
            {
                index++;
                animation = AnimationUtils.loadAnimation(this, R.anim.trans6);
                mImageview.startAnimation(animation);
                animation.setAnimationListener(this);

            }
            break;

            case 6:
                index=1;
                break;
        }
    }


    public void testPathAnimator() {
        final RelativeLayout l = findViewById(R.id.root_view);

        final ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        l.addView(imageView, param);

        Path path = new Path();
        path.moveTo(200, 200);
        path.moveTo(200, 400);
        path.moveTo(300, 400);
        path.moveTo(400, 400);
        path.moveTo(400, 600);

        PathInterpolator pathInterpolator = null;
        pathInterpolator = new PathInterpolator(0.33f, 0f, 0.12f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                l.removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                l.removeView(imageView);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        ObjectAnimator scalex = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 1.0f, 0.3f);
        ObjectAnimator scaley = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 1.0f, 0.3f);
        ObjectAnimator traslateAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", path);

        animSet.playTogether(scalex, scaley, traslateAnimator);

        animSet.setInterpolator(pathInterpolator);
        animSet.setDuration(1500);
        animSet.start();
    }


}
