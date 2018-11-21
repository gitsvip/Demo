package com.arjun.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.arjun.demo.util.LogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterV extends BaseQuickAdapter<Item, BaseViewHolder> {

    private Context context;

    MyAdapterV(@Nullable List data, Context context) {
        super(R.layout.list_v, data);
        this.context = context;
    }


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

        if (item.label.contains("2")) {


            RecyclerView rv = helper.getView(R.id.rv_z);
            LinearLayoutManager layout = new LinearLayoutManager(context);
            layout.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(layout);
            rv.setVisibility(View.VISIBLE);

            final List list = new ArrayList();
            for (int i = 0; i < 10; i++) {
                Item itemz = new Item();
                itemz.label = "index v " + i;
                list.add(itemz);
            }

            MyAdapter myAdapter = new MyAdapter(list, context);
            rv.setAdapter(myAdapter);
            rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

                    int action = motionEvent.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_MOVE:
                            recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                    }
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                    LogUtil.d(motionEvent.getAction());

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean b) {

                }
            });

        } else {
            helper.getView(R.id.rv_z).setVisibility(View.GONE);
        }
    }
}
