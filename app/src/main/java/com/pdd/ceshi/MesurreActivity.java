package com.pdd.ceshi;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pdd.ceshi.Utils.MeasureUtil;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MesurreActivity extends AppCompatActivity implements Animation.AnimationListener {


    private Animation trans;
    int index = 1;
    private float screenWidth;
    private float screenHeight;
    private ImageView mImageview, redImageview, blueImageview, yellowImageview;
    private int width;
    private int height;
    private ViewGroup group;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesurre);

        group = findViewById(R.id.root_view);
        width = MeasureUtil.getScreenSize(MesurreActivity.this)[0];
        height = MeasureUtil.getScreenSize(MesurreActivity.this)[1];

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        Log.d("0000000", "onCreate: " + width + "----" + height + "\n" + screenWidth + "--" + "   " + screenHeight);

        draw1();

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //mcontext是一个Context类型的参数

                draw();
                stop();
            }
        });

        score = 1;
        findViewById(R.id.score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
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

        stop();

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


            case 3: {

//                trans.cancel();

                if (score < 10) {
                    trans.cancel();
                } else {
                    trans.start();
                    animation = AnimationUtils.loadAnimation(this, R.anim.trans4);
                    mImageview.startAnimation(animation);
                    animation.setAnimationListener(this);
                    index++;
                }
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
//
//                Drawable btnDrawable = getResources().getDrawable(R.drawable.game2);
//                group.setBackground(btnDrawable);

                TransitionDrawable td = new TransitionDrawable(new Drawable[]{getResources().getDrawable(android.R.color.transparent)
                        , getResources().getDrawable(R.mipmap.game_1)});

                group.setBackground(td);

                td.startTransition(2500);

                draw2();

                index = 1;
                break;
        }


    }


    private void draw2() {
        redImageview = new ImageView(this);
        blueImageview = new ImageView(this);
        yellowImageview = new ImageView(this);

        redImageview.setImageResource(R.mipmap.redcar);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120,
                120);
        redImageview.setLayoutParams(params);

        ViewGroup.MarginLayoutParams marginred = new ViewGroup.MarginLayoutParams(redImageview.getLayoutParams());
        int left = (int) (screenWidth * 0.50);
        int top = (int) (screenHeight * 0.67);
        marginred.leftMargin = left;
        marginred.topMargin = top;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginred);
        layoutParams.height = 120;//设置图片的高度
        layoutParams.width = 120; //设置图片的宽度
        redImageview.setLayoutParams(layoutParams);
        Animation shake1 = AnimationUtils.loadAnimation(MesurreActivity.this, R.anim.shake1);
        redImageview.startAnimation(shake1);


        blueImageview.setImageResource(R.mipmap.bluecar);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(120,
                120);
        blueImageview.setLayoutParams(params2);

        ViewGroup.MarginLayoutParams marginblue = new ViewGroup.MarginLayoutParams(blueImageview.getLayoutParams());
        int left2 = (int) (screenWidth * 0.70);
        int top2 = (int) (screenHeight * 0.35);
        marginblue.leftMargin = left2;
        marginblue.topMargin = top2;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(marginblue);
        layoutParams2.height = 120;//设置图片的高度
        layoutParams2.width = 120; //设置图片的宽度
        blueImageview.setLayoutParams(layoutParams2);
        Animation shake = AnimationUtils.loadAnimation(MesurreActivity.this, R.anim.shake);
        blueImageview.startAnimation(shake);


        yellowImageview.setImageResource(R.mipmap.yellowcar);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(120,
                120);
        yellowImageview.setLayoutParams(params3);

        ViewGroup.MarginLayoutParams marginyellow = new ViewGroup.MarginLayoutParams(yellowImageview.getLayoutParams());
        int left3 = (int) (screenWidth * 0.37);
        int top3 = (int) (screenHeight * 0.12);
        marginyellow.leftMargin = left3;
        marginyellow.topMargin = top3;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(marginyellow);
        layoutParams3.height = 120;//设置图片的高度
        layoutParams3.width = 120; //设置图片的宽度
        yellowImageview.setLayoutParams(layoutParams3);
        yellowImageview.startAnimation(shake1);


        group.addView(yellowImageview);
        group.addView(blueImageview);
        group.addView(redImageview);

    }

    private void draw1() {

        mImageview = new ImageView(this);
        mImageview.setImageResource(R.mipmap.game_logo);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120,
                120);
        mImageview.setLayoutParams(params);


        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(mImageview.getLayoutParams());

        int left = (int) (screenWidth * 0.20);
        int top = (int) (screenHeight * 0.80);
        margin.leftMargin = left;
        margin.topMargin = top;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        layoutParams.height = 120;//设置图片的高度
        layoutParams.width = 120; //设置图片的宽度
        mImageview.setLayoutParams(layoutParams);
        mImageview.setScaleType(ImageView.ScaleType.FIT_XY);//使图片充满控件大小

        group.addView(mImageview);

    }

    private void stop() {
        score++;
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
