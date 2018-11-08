package com.arjun.demo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public MyAdapter(@Nullable List data) {
        super(R.layout.list, data);    }


    @Override
    protected void convert(BaseViewHolder helper, Item item) {

        helper.setText(R.id.tv, item.label);
        helper.setBackgroundRes(R.id.iv, R.drawable.ic_launcher_background);

        helper.getView(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "000", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
