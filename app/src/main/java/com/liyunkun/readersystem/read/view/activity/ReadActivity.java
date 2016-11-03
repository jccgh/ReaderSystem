package com.liyunkun.readersystem.read.view.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
                    if (adapter != null) {
                        mChapter.setText(adapter.getCurrentPosition() + 1 + "/" + beanList.size() + "章");
                        mTitle.setText(beanList.get(adapter.getCurrentPosition()).getTitle());
                        if (seekBar != null) {
                            seekBar.setProgress(adapter.getCurrentPosition());
                        }
                    }
                    break;
            }
        }
    };
    private TextView mTitle;
    private TextView mChapter;
    private List<PageBean> beanList;
    private RvAdapter adapter;
    private PopupWindow pwBottom;
    private PopupWindow pwTop;
    private boolean isTopShow = false;
    private boolean isBottomShow = false;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initView();
        getData2Book();
//        Toast.makeText(ReadActivity.this, "" + bookId, Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        if (isBottomShow && isTopShow) {
            pwBottom.dismiss();
            pwTop.dismiss();
            isBottomShow = false;
            isTopShow = false;
        } else {
            super.onBackPressed();
        }
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
        Button bt = (Button) findViewById(R.id.bt);
        mIv = ((ImageView) findViewById(R.id.iv));
        drawable = ((AnimationDrawable) mIv.getDrawable());
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTopShow && isBottomShow) {
                    pwBottom.dismiss();
                    pwTop.dismiss();
                    isBottomShow = false;
                    isTopShow = false;
                } else {
                    pwTop.showAtLocation(mTitle, Gravity.TOP, 0, 0);
                    isTopShow = true;
                    pwBottom.showAtLocation(mTitle, Gravity.BOTTOM, 0, 0);
                    isBottomShow = true;
                }
            }
        });
    }

    private void initTopPopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.read_top_pw_item, null);
        initTopView(view);
        pwTop = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics())));
        pwTop.setOutsideTouchable(true);
        pwTop.setBackgroundDrawable(new BitmapDrawable());
    }

    private void initTopView(View view) {
        ImageView goBack = (ImageView) view.findViewById(R.id.go_back);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initBottomPopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.read_bottom_pw_item, null);
        initBottomView(view);
        pwBottom = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 128, getResources().getDisplayMetrics())));
        pwBottom.setOutsideTouchable(true);
        pwBottom.setBackgroundDrawable(new BitmapDrawable());
    }

    private void initBottomView(View view) {
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        TextView lastChapter = (TextView) view.findViewById(R.id.last_chapter);
        TextView nextChapter = (TextView) view.findViewById(R.id.next_chapter);
        seekBar.setMax(beanList.size());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mRv.scrollToPosition(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        lastChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null && adapter.getCurrentPosition() > 1) {
                    mRv.scrollToPosition(adapter.getCurrentPosition() - 1);
                } else {
                    Toast.makeText(ReadActivity.this, "亲，当前已是第一章", Toast.LENGTH_SHORT).show();
                }
            }
        });
        nextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null && adapter.getCurrentPosition() < beanList.size() - 1) {
                    mRv.scrollToPosition(adapter.getCurrentPosition() + 1);
                } else {
                    Toast.makeText(ReadActivity.this, "亲，当前已是最后一章", Toast.LENGTH_SHORT).show();
                }
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
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRv.setLayoutManager(manager);
            adapter = new RvAdapter(beanList, this);
            mRv.setAdapter(adapter);
            initBottomPopupWindow();
            initTopPopupWindow();
        }
    }

    private void hintNoData() {
        mIv.setVisibility(View.GONE);
        mLayout.setVisibility(View.VISIBLE);
        drawable.stop();
    }
}
