package com.example.administrator.qianke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static com.chad.library.adapter.base.BaseQuickAdapter.SCALEIN;

public class Statistics_Fragment extends Fragment {

    private List<Record> records = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_fragment, null);

        initdata();

        recyclerView = (RecyclerView)view.findViewById(R.id.record);
        //创建布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        //创建适配器
        adapter = new RecordAdapter(R.layout.record_item, records);
        //开启动画（默认为渐显效果）
        adapter.openLoadAnimation(SCALEIN);
        //设置重复执行动画
        adapter.isFirstOnly(false);
        adapter.setUpFetchEnable(true);
        adapter.notifyDataSetChanged();


        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initdata(){

        for(int i = 0; i < 30 ; i++){
            Record record = new Record("1620520010"+String.valueOf(i),"未签到");
            records.add(record);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
