package com.liyunkun.readersystem.read.view.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.PageBean;
import com.liyunkun.readersystem.read.presenter.ReadPresenter;
import com.liyunkun.readersystem.read.view.adapter.RvAdapter;
import com.liyunkun.readersystem.read.view.intf.IReadView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadActivity extends AppCompatActivity implements IReadView {

    private ReadPresenter presenter = new ReadPresenter(this);
    private int bookId;
    private RecyclerView mRv;
    private ImageView mIv;
    private AnimationDrawable drawable;
    private RelativeLayout mLayout;
    private TextView mCurrentTime;
    private int current_position = 0;
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    private boolean isRunning = true;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String time = ReadActivity.this.format.format(new Date());
                    mCurrentTime.setText(time);
                    mHandler.sendEmptyMessageDelayed(0, 1000);
                    break;
            }
        }
    };
    private TextView mTitle;
    private TextView mChapter;
    private List<PageBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initView();
        Log.d("liyunkundebugprint", "onCreate: ");
        getData2Book();
        presenter.start(bookId);
        showNoData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    mHandler.sendEmptyMessage(0);
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
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
        mCurrentTime = ((TextView) findViewById(R.id.current_time));
        mTitle = ((TextView) findViewById(R.id.title));
        mChapter = ((TextView) findViewById(R.id.chapter));
        mIv = ((ImageView) findViewById(R.id.iv));
        drawable = ((AnimationDrawable) mIv.getDrawable());

        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void updateRv(List<PageBean> list) {
        if (list == null && list.size() == 0) {
            showNoData();
        }
        if (list != null && list.size() > 0) {
            hintNoData();
            beanList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                beanList.add(list.get(list.size() - 1 - i));
            }
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
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
