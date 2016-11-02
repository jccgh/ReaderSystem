package com.liyunkun.readersystem.read.view.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.PageBean;
import com.liyunkun.readersystem.read.presenter.ReadPresenter;
import com.liyunkun.readersystem.read.view.adapter.RvAdapter;
import com.liyunkun.readersystem.read.view.intf.IReadView;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity implements IReadView {

    private ReadPresenter presenter = new ReadPresenter(this);
    private int bookId;
    private RecyclerView mRv;
    private ImageView mIv;
    private AnimationDrawable drawable;
    private RelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initView();
        getData2Book();
        presenter.start(bookId);
        showNoData();
    }

    private void showNoData() {
        mIv.setVisibility(View.VISIBLE);
        mLayout.setVisibility(View.GONE);
        drawable.start();
    }

    private void getData2Book() {
        bookId = getIntent().getIntExtra("bookId", 1);
    }

    private void initView() {
        mRv = ((RecyclerView) findViewById(R.id.rv));
        mLayout = ((RelativeLayout) findViewById(R.id.rv_layout));
        mIv = ((ImageView) findViewById(R.id.iv));
        drawable = ((AnimationDrawable) mIv.getDrawable());
    }

    @Override
    public void updateRv(List<PageBean> list) {
        if (list == null && list.size() == 0) {
            showNoData();
        }
        if (list != null && list.size() > 0) {
            hintNoData();
            List<PageBean> beanList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                beanList.add(list.get(list.size() - 1 - i));
            }
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRv.setLayoutManager(manager);
            RvAdapter adapter = new RvAdapter(beanList, this);
            mRv.setAdapter(adapter);
        }
    }

    private void hintNoData() {
        mIv.setVisibility(View.GONE);
        mLayout.setVisibility(View.VISIBLE);
        drawable.stop();
    }
}
