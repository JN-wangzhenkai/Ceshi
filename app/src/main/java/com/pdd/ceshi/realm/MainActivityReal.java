package com.pdd.ceshi.realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pdd.ceshi.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主界面
 */
public class MainActivityReal extends BaseActivity {
    @BindView(R.id.toolBar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar.setTitle("DemoRealm");
        setSupportActionBar(mToolbar);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_realm_main;
    }



    @OnClick({R.id.btn_add,  R.id.btn_query,R.id.btn_async})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                startActivity(new Intent(MainActivityReal.this,DogListActivity.class));
                break;
            case R.id.btn_query:
                startActivity(new Intent(MainActivityReal.this,QueryActivity.class));
                break;
            case R.id.btn_async:
                startActivity(new Intent(MainActivityReal.this,AsyncActivity.class));

                break;
            default:
                break;
        }
    }
}
