package com.pdd.ceshi.realm;

import android.content.Context;
import android.view.View;

import com.pdd.ceshi.R;

import java.util.List;

/**
 * Created by RaphetS on 2016/10/21.
 */

public class LikeCatAdapter extends BaseAdapter<Cat> {

    public LikeCatAdapter(Context mContext, List<Cat> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, Cat cat) {
        holder.setText(R.id.tv_name, cat.getName())
                .setText(R.id.tv_id,cat.getId())
                .setVisible(R.id.iv_like, View.INVISIBLE);
    }
}
