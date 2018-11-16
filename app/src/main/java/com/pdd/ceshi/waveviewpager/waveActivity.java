package com.pdd.ceshi.waveviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.pdd.ceshi.R;

import java.util.ArrayList;
import java.util.List;


public class waveActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> viewList = new ArrayList<View>();
    private int[] ids = { R.drawable.wave, R.drawable.wave};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);

        viewPager=findViewById(R.id.viewpager);

        for (int i = 0; i < ids.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(ids[i]);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            viewList.add(iv);
        }

        viewPager.setAdapter(new MyAdapter(viewList));

        thread.start();
    }


    class MyAdapter extends PagerAdapter{

        private List<View>views;

        public MyAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return 5000;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView= (ImageView) views.get(position);
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; //
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == (object);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }


    Thread thread=new Thread(){
        @Override
        public void run() {
            while (true) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });

            }

        }
    };

}
