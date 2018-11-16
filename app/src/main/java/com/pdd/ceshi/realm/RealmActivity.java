package com.pdd.ceshi.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pdd.ceshi.R;

import io.realm.Realm;

public class RealmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
    }

    private void add(){
        Realm realm=Realm.getDefaultInstance();

    }
}
