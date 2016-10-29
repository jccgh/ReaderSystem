package com.liyunkun.readersystem.student.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.student.module.bean.RankLvBean;
import com.liyunkun.readersystem.student.view.adapter.RankLvAdapter;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mGoBack;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initView();
        initData2Lv();
        mGoBack.setOnClickListener(this);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void initData2Lv() {
        List<RankLvBean> lvBeen = new ArrayList<>();
        lvBeen.add(new RankLvBean(R.mipmap.ic_launcher, "总排行"));
        lvBeen.add(new RankLvBean(R.mipmap.ic_launcher, "畅销榜"));
        lvBeen.add(new RankLvBean(R.mipmap.ic_launcher, "推荐榜"));
        lvBeen.add(new RankLvBean(R.mipmap.ic_launcher, "状态榜"));
        lvBeen.add(new RankLvBean(R.mipmap.ic_launcher, "新书榜"));
        lvBeen.add(new RankLvBean(R.drawable.mycollect, "字数榜"));
        lvBeen.add(new RankLvBean(R.mipmap.ic_launcher, "周排行"));
        RankLvAdapter adapter = new RankLvAdapter(lvBeen, this);
        mLv.setAdapter(adapter);
    }

    private void initView() {
        mGoBack = ((ImageView) findViewById(R.id.go_back));
        mLv = ((ListView) findViewById(R.id.lv));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                finish();
                break;
        }
    }
}
