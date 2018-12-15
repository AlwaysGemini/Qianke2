package com.example.administrator.qianke;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class RecordAdapter extends BaseQuickAdapter<Record,BaseViewHolder> {
    public RecordAdapter(int layoutResId, @Nullable List<Record> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Record item) {
        helper.setText(R.id.student_id,item.getStudent_id())
        .setText(R.id.state,item.getState());
    }
}
