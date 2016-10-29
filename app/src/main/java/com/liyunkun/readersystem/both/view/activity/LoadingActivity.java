package com.liyunkun.readersystem.both.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.presenter.LoadingPresenter;
import com.liyunkun.readersystem.both.view.adapter.LoadingVpAdapter;
import com.liyunkun.readersystem.both.view.intf.ILoadingV;
import com.liyunkun.readersystem.utils.MyConstants;

//引导界面，实现view中的接口
public class LoadingActivity extends BaseActivity implements ILoadingV {

    private ViewPager mVp;//viewpager
    private Button mBtJump;//跳过的按钮
    private Button mBtOpen;//进入的按钮
    private int count;//图片的总数
    private LoadingPresenter presenter;//中间者
    private LinearLayout mLayout;//圆点的布局
    private int lastPosition = 0;//上一个图片的position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        //查找控件
        findView();
        //设置用户是否第一次安装的信息
        getSharedPreferences(MyConstants.FIRSTDOWN, MODE_PRIVATE).edit().putBoolean("isLast", false).commit();
        //开始关联view和module
        presenter.load();
//        initView2Layout();
        //跳过按钮的监听事件
        mBtJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                finish();
            }
        });
        //进入按钮的监听事件
        mBtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                finish();
            }
        });
        //viewpager的监听事件
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置当前图片对应的圆点为true
                mLayout.getChildAt(position).setEnabled(true);
                //设置上一个图片对应的圆点为false
                mLayout.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
                //判断是否为最后一张图片
                if (++position == count) {
                    showBtOpen();
                } else {
                    hideBtOpen();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 查找控件和初始化属性
     */
    private void findView() {
        presenter = new LoadingPresenter(this);

        mVp = ((ViewPager) findViewById(R.id.vp));
        mBtJump = ((Button) findViewById(R.id.bt_jump));
        mBtOpen = ((Button) findViewById(R.id.bt_open));
        mLayout = ((LinearLayout) findViewById(R.id.layout));
    }

    /**
     * 设置viewpager的资源
     *
     * @param adapter viewpager所需要的adapter，继承PagerAdapter
     */
    @Override
    public void initData2ViewPager(LoadingVpAdapter adapter) {
        mVp.setAdapter(adapter);
        //通过adapter中的getCount方法获取图片的总个数
        count = adapter.getCount();
    }

    /**
     * 设置底部小圆点
     */
    @Override
    public void initView2Layout() {
        //通过图片的个数来确定小圆点的个数
        for (int i = 0; i < count; i++) {
            //定义一个LayoutParams，用于设置小圆点之间的距离
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics())),
                    (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics())));
            //设置左外边距
            lp.leftMargin = 20;
            //定义一个view，用于绘画圆
            View view = new View(this);
            //设置view的LayoutParams
            view.setLayoutParams(lp);
            //添加背景资源
            view.setBackgroundResource(R.drawable.dot_bg);
            view.setEnabled(false);
            mLayout.addView(view);
        }
        //设置第一个小圆点的enable属性为true
        mLayout.getChildAt(0).setEnabled(true);
    }

    /**
     * 显示进入按钮
     */
    @Override
    public void showBtOpen() {
        mBtOpen.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏进入按钮
     */
    @Override
    public void hideBtOpen() {
        mBtOpen.setVisibility(View.GONE);
    }
}
