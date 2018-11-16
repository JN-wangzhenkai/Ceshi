package com.pdd.ceshi.dbmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdd.ceshi.R;

import java.util.List;

public class HistoryAdapter extends BaseAdapter{

    private Context mContext;
    private List<RecordBean>mdata;

    public HistoryAdapter(List<RecordBean> mdata,Context mContext) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    //刷新适配器更新数据
    public void refresh(List<RecordBean>data){
        this.mdata=data;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.activity_search_history_listview_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.search_history_tv);
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.search_history_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RecordBean bean = mdata.get(position);
        holder.textView.setText(bean.getTime());

        return convertView;
    }

    private  class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
