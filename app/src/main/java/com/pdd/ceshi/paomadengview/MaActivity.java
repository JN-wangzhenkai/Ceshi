package com.pdd.ceshi.paomadengview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.pdd.ceshi.R;

public class MaActivity extends AppCompatActivity {
    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma);
        marqueeView = (MarqueeView) findViewById(R.id.marquee_view);
        initMarqueeView();
    }

    private void initMarqueeView(){
        ImageView iv1 = new ImageView(this);
        iv1.setImageResource(R.drawable.wave);
        marqueeView.addViewInQueue(iv1);
        ImageView iv2 = new ImageView(this);
        iv2.setImageResource(R.drawable.wave);
        marqueeView.addViewInQueue(iv2);
        ImageView iv3 = new ImageView(this);
        iv2.setImageResource(R.drawable.wave);
        marqueeView.addViewInQueue(iv3);

        marqueeView.setScrollSpeed(20);
        marqueeView.setScrollDirection(MarqueeView.LEFT_TO_RIGHT);
//        marqueeView.setViewMargin(15);
        marqueeView.startScroll();
    }
}


