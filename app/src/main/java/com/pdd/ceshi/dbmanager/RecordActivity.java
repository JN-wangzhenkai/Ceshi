package com.pdd.ceshi.dbmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.pdd.ceshi.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RecordActivity extends AppCompatActivity {


    private List<String> sea_data;
    static List<String> list = new ArrayList<String>();
    private ListView mSearchAcHistoryLv;

    private RecordHelper helper;
    private HistoryAdapter his_adapter;
    private List<RecordBean> his_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                String time=simpleDateFormat.format(date);

                insertHistory(time+"器材"+"nayi");
            }
        });

        mSearchAcHistoryLv = (ListView) findViewById(R.id.search_ac_layout_rl_search_history_lv);

        mSearchAcHistoryLv.setHeaderDividersEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showHistoryList();     //显示历史记录

    }

    /**
     * 显示历史记录
     */
    public void showHistoryList() {
        helper = new RecordHelper(getApplicationContext());
        his_data = queryHistoryData();


        Collections.reverse(his_data);
        if (his_data.size() > 0) {
            mSearchAcHistoryLv.setHeaderDividersEnabled(true);
        }
        his_adapter = new HistoryAdapter(his_data, getApplication());

        mSearchAcHistoryLv.setAdapter(his_adapter);
    }


    // 将每一行的数据封装成一个HistoryShowBean对象，然后放到his_list中
    private List<RecordBean> queryHistoryData() {
        helper = new RecordHelper(getApplicationContext());

        List<RecordBean> his_list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DBConstants.HISTORY_TABLENAME;
        Cursor his_c = db.rawQuery(selectQuery, null);

        his_c.moveToFirst();
        while (!his_c.isAfterLast()) {
            int h_id = his_c.getInt(his_c.getColumnIndex("_id"));
            String h_name = his_c.getString(his_c.getColumnIndex("h_name"));

            // 用一个HistoryShowBean类来封装得到的数据
            final RecordBean his_bean = new RecordBean();
            his_bean.setTime(h_name);
            his_list.add(his_bean);
            his_c.moveToNext();
        }

        db.close();
        return his_list;

    }


    /*
     * 删除历史记录
     */
    private void deleteHistory() {

        SQLiteDatabase db = helper.getWritableDatabase();
        // 删除表。
        db.execSQL("delete from " + DBConstants.HISTORY_TABLENAME);

        handler.sendEmptyMessage(1);

        db.close();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    // 点击清除记录按钮，刷新界面
                    his_adapter.refresh(queryHistoryData());
                    break;

                default:
                    break;
            }
        }
    };


    /*
     * 插入历史记录，点击按钮写到数据库
     */
    private void insertHistory(String search) {
        helper = new RecordHelper(getApplicationContext());
        // 插入数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = 0;
        // 查询数据库，判断edittext的内容是否已经存在，如果存在了，就不写了，如果不存在，就插入数据库
        // 取回查询存放history表的h_name列的list集合
        List<String> list = queryHistorySql();
        for (int i = 0; i < list.size(); i++) {
            // 获取搜索框的输入内容，和数据已经存在的记录比对，如果有一样的，就count增加；先删除后插入
            if (list.get(i).equals(search)) {
                db.execSQL("delete from " + DBConstants.HISTORY_TABLENAME
                        + " where h_name='" + search + "'");
                count++;
            }
        }
        // 如果count == 0，说明没有重复的数据，就可以插入数据库history表中
        if (count == 0) {
            db.execSQL("insert into " + DBConstants.HISTORY_TABLENAME
                    + " values(?,?)", new Object[]{null, search});
        } else {
            db.execSQL("insert into " + DBConstants.HISTORY_TABLENAME
                    + " values(?,?)", new Object[]{null, search});
        }

        db.close();
    }


    /*
     * 查询数据库的h_name一列，然后放到list集合中，用于判断是否插入数据
     */
    private List<String> queryHistorySql() {
        helper = new RecordHelper(getApplicationContext());
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "
                + DBConstants.HISTORY_TABLENAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // 查询数据库，取出h_name这一列，然后全部放到list集合中，在前面调用此方法的时候，用来判断
            String name = cursor.getString(cursor.getColumnIndex("h_name"));
            list.add(name);

            cursor.moveToNext();
        }

        db.close();
        // 返回一个list集合
        return list;
    }
}
