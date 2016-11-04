package com.liyunkun.readersystem.read.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.read.view.adapter.CatalogAdapter;
import com.liyunkun.readersystem.read.view.fragment.BookMarkFragment;
import com.liyunkun.readersystem.read.view.fragment.CatalogFragment;
import com.liyunkun.readersystem.read.view.fragment.NoteFragment;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mOrder;
    private ViewPager mVp;
    private TabLayout mTabLayout;
    private TextView mBookName;
    private String[] titles;
    private String[] titles1;
    private List<Fragment> fragmentList;
    private BookBean bookBean;
    private CatalogFragment catalogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        initView();
        initOrderImage();
        initData();
    }

    private void initOrderImage() {
        if (MyConstants.order_mode == MyConstants.ASC) {
            mOrder.setImageResource(R.mipmap.ic_launcher);
        } else if (MyConstants.order_mode == MyConstants.DESC) {
            mOrder.setImageResource(R.mipmap.ic_launcher);
        }
    }

    private void initData() {
        titles = new String[]{"目录", "书签", "笔记"};
        fragmentList = new ArrayList<>();
        catalogFragment = new CatalogFragment();
        catalogFragment.setTitles(titles1);
        fragmentList.add(catalogFragment);
        fragmentList.add(new BookMarkFragment());
        fragmentList.add(new NoteFragment());

        CatalogAdapter adapter = new CatalogAdapter(getSupportFragmentManager(), titles, fragmentList);
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initView() {
        Intent intent = getIntent();
        bookBean = ((BookBean) intent.getSerializableExtra("bookBean"));
        titles1 = intent.getStringArrayExtra("titles");
        mOrder = ((ImageView) findViewById(R.id.order));
        mVp = ((ViewPager) findViewById(R.id.vp));
        mTabLayout = ((TabLayout) findViewById(R.id.tabLayout));
        mBookName = ((TextView) findViewById(R.id.book_name));

        mBookName.setText(bookBean.getName());
        mOrder.setOnClickListener(this);

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        showOrderImage();
                        break;
                    case 1:
                        hideOrderImage();
                        break;
                    case 2:
                        hideOrderImage();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void showOrderImage() {
        mOrder.setVisibility(View.VISIBLE);
    }

    private void hideOrderImage() {
        mOrder.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order:
                Log.d("liyunkundebugprint", "onClick: ");
                updateOrderImage();
                break;
        }
    }

    private void updateOrderImage() {
        if (MyConstants.order_mode == MyConstants.ASC) {
            mOrder.setImageResource(R.mipmap.ic_launcher);
            MyConstants.order_mode=MyConstants.DESC;
            catalogFragment.updateData2Lv();
        } else if (MyConstants.order_mode == MyConstants.DESC) {
            mOrder.setImageResource(R.mipmap.ic_launcher);
            MyConstants.order_mode=MyConstants.ASC;
            catalogFragment.updateData2Lv();
        }
    }
}
