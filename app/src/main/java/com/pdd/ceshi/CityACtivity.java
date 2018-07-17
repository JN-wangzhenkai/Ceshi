package com.pdd.ceshi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *选择城市列表
 */

public class CityACtivity extends AppCompatActivity {

    private City city;
    private ArrayList<City> toCitys;


    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        tv=findViewById(R.id.city);
        city = new City();


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CityACtivity.this, CitySelect1Activity.class);
                in.putExtra("city", city);
                startActivityForResult(in, 1);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 8) {
            if (requestCode == 1) {
                city = data.getParcelableExtra("cityACtivity");
                //获取到省市区
                tv.setText(city.getProvince() + city.getCity() + city.getDistrict());

            } else if (requestCode == 2) {
                toCitys = data.getParcelableArrayListExtra("toCitys");
                StringBuffer ab = new StringBuffer();
                for (int i = 0; i < toCitys.size(); i++) {
                    if (i == toCitys.size() - 1) {//如果是最后一个城市就不需要逗号
                        ab.append(toCitys.get(i).getCity());
                    } else {
                        ab.append(toCitys.get(i).getCity() + "， ");//如果不是最后一个城市就需要逗号
                    }
                }
            }
        }
    }
}
