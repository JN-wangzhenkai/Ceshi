package com.pdd.ceshi.paomadengview;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.pdd.ceshi.R;

import java.util.ArrayList;
import java.util.List;

public class HoriActivity extends AppCompatActivity implements RecyAdapter.OnItemClickListener {
    private String TAG = "HorizontalActivity";

    ImageView img;
    RecyclerView recyclerview;


    private Integer[] mImgIds = {R.drawable.wave, R.drawable.wave, R.drawable.wave};
    private List<Integer> datas;
    private RecyAdapter recyAdapter;
    private Handler mHandler = new Handler();
    private LinearLayoutManager layoutManager;

    private int oldItem = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hori);

        img=findViewById(R.id.img);
        recyclerview=findViewById(R.id.recyclerview);

        initData();
        initRecy();
        img.setImageResource(datas.get(0));
        recyAdapter.setOnItemClickListener(this);
    }


    Runnable scrollRunnable = new Runnable() {
        @Override
        public void run() {
            recyclerview.scrollBy(5, 0);

            int firstItem = layoutManager.findFirstVisibleItemPosition();
            if (firstItem != oldItem && firstItem > 0) {
                oldItem = firstItem;
                img.setImageResource(datas.get(oldItem % datas.size()));
            }


            mHandler.postDelayed(scrollRunnable, 10);
        }
    };

    private void initRecy() {
        recyAdapter = new RecyAdapter(this, datas);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(recyAdapter);
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < mImgIds.length; i++) {
            datas.add(mImgIds[i]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(scrollRunnable, 10);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacks(scrollRunnable);
    }

    @Override
    public void onItemClick(View view, int tag) {
        Toast.makeText(this, "第" + tag + "张图片被点击了", Toast.LENGTH_SHORT).show();
    }
}

