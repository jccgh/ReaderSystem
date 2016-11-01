package com.liyunkun.readersystem.read.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.PageBean;
import com.liyunkun.readersystem.read.presenter.ReadPresenter;
import com.liyunkun.readersystem.read.view.adapter.RvAdapter;
import com.liyunkun.readersystem.read.view.intf.IReadView;

import java.util.List;

public class ReadActivity extends AppCompatActivity implements IReadView{

    private RecyclerView mRv;
    private ReadPresenter presenter=new ReadPresenter(this);
    private int bookId;
//    private MyBook book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initView();
        getData2Book();
        presenter.start(bookId);
    }

    private void getData2Book() {
        bookId = getIntent().getIntExtra("bookId",1);
    }

    private void initView() {
        mRv = ((RecyclerView) findViewById(R.id.rv));
    }

    @Override
    public void updateRv(List<PageBean> list) {
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRv.setLayoutManager(manager);
        RvAdapter adapter=new RvAdapter(list,this);
        mRv.setAdapter(adapter);
    }
}
