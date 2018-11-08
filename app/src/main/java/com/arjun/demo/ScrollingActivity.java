package com.arjun.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {
    private CollapsingToolbarLayout toolbarLayout;
    private AppBarLayout appBarLayout;
    private TabLayout tab,tab2;
    private RecyclerView rv,rv_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        tab = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
//        toolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
        toolbarLayout.setTitle("1/2");
        toolbar.setTitle("点击");
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);

        appBarLayout = findViewById(R.id.app_bar);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i == 0) {
                    //expend
                    toolbarLayout.setTitle("Title");
                    tab.setVisibility(View.GONE);
                    tab2.setVisibility(View.VISIBLE);

                }else if (Math.abs(i) == appBarLayout.getTotalScrollRange()){
                    toolbarLayout.setTitle("1/12");
                    tab.setVisibility(View.VISIBLE);
                    tab2.setVisibility(View.GONE);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(layout);

        LinearLayoutManager layoutV = new LinearLayoutManager(this);
        layoutV.setOrientation(LinearLayoutManager.VERTICAL);
        rv_v = findViewById(R.id.rvv);
        rv_v.setLayoutManager(layoutV);

        final List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.label = "index " + i;
            list.add(item);
        }
        MyAdapter myAdapter = new MyAdapter(list);
        rv.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ScrollingActivity.this,    ((Item) adapter.getItem(position)).label.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        MyAdapterV myAdapterV = new MyAdapterV(list,ScrollingActivity.this);
        rv_v.setAdapter(myAdapterV);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
