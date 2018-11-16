package com.pdd.ceshi;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.pdd.ceshi.WeChatImage.CustomWebView;

public class ProductDetailActivity extends AppCompatActivity implements DragSwitchLayout.DragSwitchListener {

    private TextView mTitletv;

    // 拖拽自动切换布局的容器
    private DragSwitchLayout mMainContainer;
    //    // 标题模块
    //    private TitleFragment mTitleFragment;
    // 图文详情模块
    private ImgDeatailFragment mImgDeatailFragment;
    //商品详情模块

    // 是否初次加载
    private boolean mIsFirstLoad = true;
    private CustomScrollView customScrollView;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        customScrollView = (CustomScrollView) findViewById(R.id.sv_good_detail_text_detail_container);//CustomScrollView
        customScrollView.setFillViewport(true);
        fragmentManager = getSupportFragmentManager();
        mTitletv = (TextView) findViewById(R.id.tv_top_title);


        //初始化fragment
        mImgDeatailFragment = new ImgDeatailFragment();

        mMainContainer = (DragSwitchLayout) findViewById(R.id.svc_good_detail_main_container);



        mMainContainer.setDragSwitchListener(this);
    }

    @Override
    public void onDragToBottomView() {
        mTitletv.setText("图文详情");

        fragmentManager.beginTransaction().replace(R.id.sv_good_detail_img_detail_container, mImgDeatailFragment).commit();

        Log.d("22222", "onDragToBottomView: "+"dfadsfa");

    }

    @Override
    public void onDragToTopView() {
        mTitletv.setText("商品详情");
        Log.d("22222", "onDragToBottomView: "+"000000000");

    }

}
