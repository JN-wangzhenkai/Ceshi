package com.pdd.ceshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCeshi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_ceshi);


        x.Ext.init(getApplication());
        x.isDebug();

        subMit();
    }

    private void subMit(){

        List<Object> cpList=new ArrayList<>();

        Map<String,String> map =new HashMap<String, String>();
        map.put("quantity","1");
        map.put("product_id","");
        map.put("price","");
        map.put("path","");
        map.put("product","");
        cpList.add(map);

        String toJson = new Gson().toJson(cpList);
        Log.e("11111", "onClick: >>>>>>>>>>>>>>>"+toJson ); //看好看看log

        RequestParams params =new RequestParams("http://60.191.35.250:8083/oxygen-orderMgr-webServer/orderMgr/orderCreate/");


        params.addBodyParameter("userId", "1");
        params.addBodyParameter("goodId","55555");
        params.addBodyParameter("shopId","hzpddylypyxgs");
        params.addBodyParameter("shopGoodsRelationId","1");
        params.addBodyParameter("userAddressId","1");
        params.addBodyParameter("quantity","1");

        Log.d("1111111", "subMit: "+params);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("1111111", "onSuccess: "+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("1111111", "onError: "+ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
