package com.liyunkun.readersystem.student.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.student.presenter.SearchPresenter;
import com.liyunkun.readersystem.student.view.intf.ISearchView;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, ISearchView {

    private EditText mInputWorld;
    private ListView mLv;
    private FlexboxLayout mHotLayout;
    private SearchPresenter presenter = new SearchPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        presenter.start();
    }


    private void initView() {
        ImageView goBack = (ImageView) findViewById(R.id.go_back);
        mInputWorld = ((EditText) findViewById(R.id.search_et));
        TextView search = (TextView) findViewById(R.id.search_tv);
        TextView replace = (TextView) findViewById(R.id.replace);
        TextView clear = (TextView) findViewById(R.id.clear);
        mLv = ((ListView) findViewById(R.id.lv));
        mHotLayout = ((FlexboxLayout) findViewById(R.id.hot_layout));

        goBack.setOnClickListener(this);
        search.setOnClickListener(this);
        replace.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back://返回上一个界面
                finish();
                break;
            case R.id.search_tv://搜索
                break;
            case R.id.replace://换一换
                break;
            case R.id.clear://清空历史
                break;
        }
    }

    @Override
    public void initView2HotLayout(List<String> list) {
        for (String s : list) {
            TextView tv = new TextView(this);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 25;
            lp.bottomMargin=25;
            tv.setLayoutParams(lp);
            tv.setBackgroundResource(R.drawable.hot_layout_tv_bg);
            tv.setPadding(15, 15, 15, 15);
            tv.setText(s);
            mHotLayout.addView(tv);
        }
    }
}
