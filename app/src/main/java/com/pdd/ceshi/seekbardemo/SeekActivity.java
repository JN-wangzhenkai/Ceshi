package com.pdd.ceshi.seekbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.pdd.ceshi.R;

public class SeekActivity extends AppCompatActivity {

    private SeekBar mSeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        mSeek=findViewById(R.id.seekbar);
        mSeek.setProgress(30);
    }
}
