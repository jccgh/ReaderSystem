package com.liyunkun.readersystem.read.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liyunkun.readersystem.MyApp;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.both.module.bean.PageBean;
import com.liyunkun.readersystem.read.module.bean.BookMark;
import com.liyunkun.readersystem.read.module.bean.BookMarkDao;
import com.liyunkun.readersystem.read.presenter.ReadPresenter;
import com.liyunkun.readersystem.read.view.adapter.RvAdapter;
import com.liyunkun.readersystem.read.view.intf.IReadView;
import com.liyunkun.readersystem.utils.MyConstants;
import com.liyunkun.readersystem.utils.ShareUtil;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadActivity extends AppCompatActivity implements IReadView, View.OnClickListener {

    private ReadPresenter presenter = new ReadPresenter(this);
    private RecyclerView mRv;
    private ImageView mIv;
    private AnimationDrawable drawable;
    private RelativeLayout mLayout;
    private TextView mCurrentTime;
    private int current_position = 0;
    private SimpleDateFormat format;
    private boolean isRunning = true;
    private Handler mHandler;
    private TextView mTitle;
    private TextView mChapter;
    private List<PageBean> beanList;
    private RvAdapter adapter;
    private PopupWindow pwBottom;
    private PopupWindow pwTop;
    private boolean isTopShow = false;
    private boolean isBottomShow = false;
    private boolean isSettingShow = false;
    private boolean isMoreShow = false;
    private SeekBar seekBar;
    private SharedPreferences sp;
    private BookBean bookBean;
    private LayoutInflater inflater;
    private PopupWindow pwSetting;
    private ImageView lineSpaceDefault;
    private ImageView lineSpace15;
    private ImageView lineSpace1;
    private ImageView lineSpace02;
    private ImageView readingBg4;
    private ImageView readingBg5;
    private ImageView readingBgDufault;
    private ImageView readingBgEye;
    private ImageView readingBgKraft;
    private ImageView readingBgNight1;
    private ImageView readingBgNight2;
    private ImageView readingBgPowerless;
    private ImageView readingBgSoft;
    private RelativeLayout mRootLayout;
    private ImageView bookMark;
    private BookMarkDao bookMarkDao;
    private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private PopupWindow morePw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        initView();
        getData2Book();
        presenter.start(bookBean.getBookId());
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
    protected void onResume() {
        super.onResume();
        current_position = sp.getInt(MyConstants.READ_POSITION + bookBean.getBookId(), current_position);
        MyConstants.default_text_size = sp.getFloat(MyConstants.userName + "textSize", MyConstants.default_text_size);
        MyConstants.line_height = sp.getFloat(MyConstants.userName + "lineSpace", MyConstants.default_line_height);
        MyConstants.reading_bg = sp.getInt(MyConstants.userName + "readingBg", MyConstants.default_reading_bg);
        if (adapter != null && mRv != null) {
            mRv.scrollToPosition(current_position);
            adapter.notifyItemChanged(current_position);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adapter != null) {
            sp.edit()
                    .putFloat(MyConstants.userName + "lineSpace", MyConstants.line_height)
                    .putFloat(MyConstants.userName + "textSize", MyConstants.default_text_size)
                    .putInt(MyConstants.userName + "readingBg", MyConstants.reading_bg)
                    .putInt(MyConstants.READ_POSITION + bookBean.getBookId(), adapter.getCurrentPosition())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (pwSetting != null) {
            if (!pwSetting.isShowing() && isSettingShow) {
                isSettingShow = false;
            }
        }
        if (isMoreShow) {
            morePw.dismiss();
            isMoreShow = false;
        } else if (isBottomShow && isTopShow) {
            dismissTopAndBottom();
        } else if (isSettingShow) {
            pwSetting.dismiss();
            isSettingShow = false;
        } else {
            super.onBackPressed();
        }
    }

    private void dismissTopAndBottom() {
        pwBottom.dismiss();
        pwTop.dismiss();
        isBottomShow = false;
        isTopShow = false;
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
        bookBean = ((BookBean) getIntent().getSerializableExtra("bookBean"));
    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        format = new SimpleDateFormat("HH:mm");
        mRv = ((RecyclerView) findViewById(R.id.rv));
        mLayout = ((RelativeLayout) findViewById(R.id.rv_layout));
        mCurrentTime = ((TextView) findViewById(R.id.current_time));
        mTitle = ((TextView) findViewById(R.id.title));
        mChapter = ((TextView) findViewById(R.id.chapter));
        Button bt = (Button) findViewById(R.id.bt);
        sp = getSharedPreferences(MyConstants.READ_SP, MODE_PRIVATE);
        mIv = ((ImageView) findViewById(R.id.iv));
        mRootLayout = ((RelativeLayout) findViewById(R.id.root_layout));
        bookMarkDao = ((MyApp) getApplication()).daoSession.getBookMarkDao();
        drawable = ((AnimationDrawable) mIv.getDrawable());
        mHandler = new Handler() {
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
        bt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_MOVE:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTopShow && isBottomShow) {
                    dismissTopAndBottom();
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
        ImageView more = (ImageView) view.findViewById(R.id.more);

        goBack.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    private void initBottomPopupWindow() {
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
        LinearLayout readCatalogLayout = (LinearLayout) view.findViewById(R.id.read_catalog_layout);
        LinearLayout settingLayout = (LinearLayout) view.findViewById(R.id.text_size_setting);
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
        lastChapter.setOnClickListener(this);
        nextChapter.setOnClickListener(this);
        readCatalogLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);
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
            mRv.scrollToPosition(current_position);
            adapter.notifyItemChanged(current_position);
            initBottomPopupWindow();
            initTopPopupWindow();
            showPwSetting();
        }
    }

    private void hintNoData() {
        mIv.setVisibility(View.GONE);
        mLayout.setVisibility(View.VISIBLE);
        drawable.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read_catalog_layout:
                String[] titles = new String[beanList.size()];
                for (int i = 0; i < beanList.size(); i++) {
                    titles[i] = beanList.get(i).getTitle();
                }
                Intent intent = new Intent(this, CatalogActivity.class);
                intent.putExtra("bookBean", bookBean);
                intent.putExtra("titles", titles);
                startActivity(intent);
                break;
            case R.id.next_chapter: {
                if (adapter != null && adapter.getCurrentPosition() < beanList.size() - 1) {
                    mRv.scrollToPosition(adapter.getCurrentPosition() + 1);
                    adapter.notifyItemChanged(adapter.getCurrentPosition() + 1);
                } else {
                    Toast.makeText(ReadActivity.this, "亲，当前已是最后一章", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.last_chapter: {
                if (adapter != null && adapter.getCurrentPosition() > 0) {
                    mRv.scrollToPosition(adapter.getCurrentPosition() - 1);
                    adapter.notifyItemChanged(adapter.getCurrentPosition() - 1);
                } else {
                    Toast.makeText(ReadActivity.this, "亲，当前已是第一章", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.text_size_setting: {
                dismissTopAndBottom();
                pwSetting.showAtLocation(mRv, Gravity.BOTTOM, 0, 0);
                isSettingShow = true;
            }
            break;
            case R.id.add_text_size: {
                if (MyConstants.default_text_size <= MyConstants.max_text_size) {
                    MyConstants.default_text_size += 2;
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ReadActivity.this, "亲，已经是最大的字体了", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.sub_text_size: {
                if (MyConstants.default_text_size > MyConstants.min_text_size) {
                    MyConstants.default_text_size -= 2;
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ReadActivity.this, "亲，已经是最小的字体了", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.line_space_default:
                MyConstants.line_height = MyConstants.default_line_height;
                adapter.notifyDataSetChanged();
                initLineSpace();
                break;
            case R.id.liner_space_1_5:
                MyConstants.line_height = MyConstants.line_height_15;
                adapter.notifyDataSetChanged();
                initLineSpace();
                break;
            case R.id.liner_space_1:
                MyConstants.line_height = MyConstants.line_height_10;
                adapter.notifyDataSetChanged();
                initLineSpace();
                break;
            case R.id.liner_space_0_2:
                MyConstants.line_height = MyConstants.line_height_02;
                adapter.notifyDataSetChanged();
                initLineSpace();
                break;
            case R.id.reading_bg_4:
                MyConstants.reading_bg = MyConstants.reading_bg_7;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_5:
                MyConstants.reading_bg = MyConstants.reading_bg_8;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_default:
                MyConstants.reading_bg = MyConstants.default_reading_bg;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_eye:
                MyConstants.reading_bg = MyConstants.reading_bg_1;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_kraft:
                MyConstants.reading_bg = MyConstants.reading_bg_2;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_night1:
                MyConstants.reading_bg = MyConstants.reading_bg_3;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_night2:
                MyConstants.reading_bg = MyConstants.reading_bg_4;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_powerless:
                MyConstants.reading_bg = MyConstants.reading_bg_5;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.reading_bg_soft:
                MyConstants.reading_bg = MyConstants.reading_bg_6;
                adapter.notifyDataSetChanged();
                initReadingBg();
                break;
            case R.id.go_back:
                finish();
                break;
            case R.id.more:
                toastMorePw();
                isMoreShow = true;
                break;
            case R.id.mark_layout: {
                PageBean pageBean = beanList.get(adapter.getCurrentPosition());
                if (isAddBookMark()) {
                    List<BookMark> list = bookMarkDao.queryBuilder()
                            .where(BookMarkDao.Properties.PageId.eq(pageBean.getPageId()))
                            .where(BookMarkDao.Properties.BookId.eq(bookBean.getBookId()))
                            .where(BookMarkDao.Properties.UserName.eq(MyConstants.userName))
                            .list();
                    if (list != null && list.size() > 0) {
                        bookMarkDao.delete(list.get(0));
                        Toast.makeText(ReadActivity.this, "书签已删除", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String time = this.format2.format(new Date());
                    bookMarkDao.save(new BookMark(time, null, pageBean.getMessage(),
                            pageBean.getTitle(), pageBean.getPageId(), MyConstants.userName, bookBean.getBookId()));
                    Toast.makeText(ReadActivity.this, "书签添加成功", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.share_layout: {
                ShareUtil.share(bookBean, this);
            }
            break;


        }
    }

    private void toastMorePw() {
        View view = inflater.inflate(R.layout.read_more_pw_item, null);
        initMoreView(view);
        morePw = new PopupWindow(view,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics())),
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, getResources().getDisplayMetrics())));
        morePw.setOutsideTouchable(true);
        morePw.setBackgroundDrawable(new BitmapDrawable());
        morePw.showAtLocation(mRv, Gravity.TOP,
                getResources().getDisplayMetrics().widthPixels - morePw.getWidth() - (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics())),
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 85, getResources().getDisplayMetrics())));
    }

    private void initMoreView(View view) {
        ImageView bookImg = (ImageView) view.findViewById(R.id.book_img);
        Picasso.with(this).load(bookBean.getBookImg()).into(bookImg);
        TextView bookName = (TextView) view.findViewById(R.id.book_name);
        bookName.setText(bookBean.getName());
        TextView author = (TextView) view.findViewById(R.id.author);
        author.setText(bookBean.getAuthor());
        bookMark = ((ImageView) view.findViewById(R.id.book_mark));
        LinearLayout markLayout = (LinearLayout) view.findViewById(R.id.mark_layout);
        markLayout.setOnClickListener(this);
        initBookMark();
        LinearLayout shareLayout = (LinearLayout) view.findViewById(R.id.share_layout);
        shareLayout.setOnClickListener(this);
    }

    private boolean isAddBookMark() {
        List<BookMark> list = bookMarkDao.queryBuilder()
                .where(BookMarkDao.Properties.PageId.eq(beanList.get(adapter.getCurrentPosition()).getPageId()))
                .where(BookMarkDao.Properties.BookId.eq(bookBean.getBookId()))
                .where(BookMarkDao.Properties.UserName.eq(MyConstants.userName))
                .list();
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    private void initBookMark() {
        if (isAddBookMark()) {
            bookMark.setImageResource(R.drawable.read_bookmark);
        } else {
            bookMark.setImageResource(R.drawable.read_bookmark);
        }
    }

    private void showPwSetting() {
        View view = inflater.inflate(R.layout.read_setting_pw_item, null);
        initSettingView(view);
        pwSetting = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 210, getResources().getDisplayMetrics())));
        pwSetting.setOutsideTouchable(true);
        pwSetting.setBackgroundDrawable(new BitmapDrawable());
    }

    private void initSettingView(View view) {
        ImageView addTextSize = (ImageView) view.findViewById(R.id.add_text_size);
        ImageView subTextSize = (ImageView) view.findViewById(R.id.sub_text_size);
        lineSpaceDefault = (ImageView) view.findViewById(R.id.line_space_default);
        lineSpace15 = (ImageView) view.findViewById(R.id.liner_space_1_5);
        lineSpace1 = (ImageView) view.findViewById(R.id.liner_space_1);
        lineSpace02 = (ImageView) view.findViewById(R.id.liner_space_0_2);
        readingBg4 = ((ImageView) view.findViewById(R.id.reading_bg_4));
        readingBg5 = ((ImageView) view.findViewById(R.id.reading_bg_5));
        readingBgDufault = ((ImageView) view.findViewById(R.id.reading_bg_default));
        readingBgEye = ((ImageView) view.findViewById(R.id.reading_bg_eye));
        readingBgKraft = ((ImageView) view.findViewById(R.id.reading_bg_kraft));
        readingBgNight1 = ((ImageView) view.findViewById(R.id.reading_bg_night1));
        readingBgNight2 = ((ImageView) view.findViewById(R.id.reading_bg_night2));
        readingBgPowerless = ((ImageView) view.findViewById(R.id.reading_bg_powerless));
        readingBgSoft = ((ImageView) view.findViewById(R.id.reading_bg_soft));

        initLineSpace();
        initReadingBg();

        addTextSize.setOnClickListener(this);
        subTextSize.setOnClickListener(this);
        lineSpaceDefault.setOnClickListener(this);
        lineSpace15.setOnClickListener(this);
        lineSpace1.setOnClickListener(this);
        lineSpace02.setOnClickListener(this);
        readingBgSoft.setOnClickListener(this);
        readingBgPowerless.setOnClickListener(this);
        readingBgNight2.setOnClickListener(this);
        readingBgNight1.setOnClickListener(this);
        readingBgKraft.setOnClickListener(this);
        readingBgEye.setOnClickListener(this);
        readingBgDufault.setOnClickListener(this);
        readingBg5.setOnClickListener(this);
        readingBg4.setOnClickListener(this);

    }

    /**
     * =1;//eye
     * =2;//kraft
     * =3;//night1
     * =4;//night2
     * =5;//powerless
     * =6;//soft
     * =7;//4
     * =8;//5
     */
    private void initReadingBg() {
        switch (MyConstants.reading_bg) {
            case 0://default
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_default));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default_select);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
            case 1://eye
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_eye));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye_select);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
            case 2://kraft
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_kraft));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft_select);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
            case 3://night1
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_night1));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1_select);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
            case 4://night2
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_night2));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2_select);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
            case 5://powerless
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_powerless));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless_select);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
            case 6://soft
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_soft));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft_check);
                break;
            case 7://4
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_4));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4_select);
                readingBg5.setImageResource(R.drawable.reading_bg_5);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
            case 8://5
                mRootLayout.setBackgroundColor(getResources().getColor(R.color.read_bg_5));
                readingBgDufault.setImageResource(R.drawable.reading_bg_default);
                readingBgEye.setImageResource(R.drawable.reading_bg_eye);
                readingBg4.setImageResource(R.drawable.reading_bg_4);
                readingBg5.setImageResource(R.drawable.reading_bg_5_select);
                readingBgKraft.setImageResource(R.drawable.reading_bg_kraft);
                readingBgNight1.setImageResource(R.drawable.reading_bg_night1);
                readingBgNight2.setImageResource(R.drawable.reading_bg_night2);
                readingBgPowerless.setImageResource(R.drawable.reading_bg_powerless);
                readingBgSoft.setImageResource(R.drawable.reading_bg_soft);
                break;
        }
    }

    private void initLineSpace() {
        if (MyConstants.line_height == MyConstants.default_line_height) {
            lineSpaceDefault.setImageResource(R.drawable.liner_space_default_checked);
            lineSpace02.setImageResource(R.drawable.liner_space_0_2);
            lineSpace1.setImageResource(R.drawable.liner_space_1);
            lineSpace15.setImageResource(R.drawable.liner_space_1_5);
        } else if (MyConstants.line_height == MyConstants.line_height_02) {
            lineSpaceDefault.setImageResource(R.drawable.liner_space_default);
            lineSpace02.setImageResource(R.drawable.liner_space_0_2_checked);
            lineSpace1.setImageResource(R.drawable.liner_space_1);
            lineSpace15.setImageResource(R.drawable.liner_space_1_5);
        } else if (MyConstants.line_height == MyConstants.line_height_10) {
            lineSpaceDefault.setImageResource(R.drawable.liner_space_default);
            lineSpace02.setImageResource(R.drawable.liner_space_0_2);
            lineSpace1.setImageResource(R.drawable.liner_space_1_checekd);
            lineSpace15.setImageResource(R.drawable.liner_space_1_5);
        } else if (MyConstants.line_height == MyConstants.line_height_15) {
            lineSpaceDefault.setImageResource(R.drawable.liner_space_default);
            lineSpace02.setImageResource(R.drawable.liner_space_0_2);
            lineSpace1.setImageResource(R.drawable.liner_space_1);
            lineSpace15.setImageResource(R.drawable.liner_space_1_5_checked);
        }
    }
}
