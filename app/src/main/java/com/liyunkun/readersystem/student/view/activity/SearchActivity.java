package com.liyunkun.readersystem.student.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.liyunkun.readersystem.MyApp;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.both.module.bean.MyBook;
import com.liyunkun.readersystem.both.module.bean.MyBookDao;
import com.liyunkun.readersystem.read.view.activity.ReadActivity;
import com.liyunkun.readersystem.student.presenter.SearchPresenter;
import com.liyunkun.readersystem.student.view.adapter.ClassifyListLvAdapter;
import com.liyunkun.readersystem.student.view.intf.ISearchView;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, ISearchView {

    private EditText mInputWorld;
    private ListView mLv;
    private FlexboxLayout mHotLayout;
    private List<String> list = new ArrayList<>();
    private Random random = new Random();
    private SearchPresenter presenter = new SearchPresenter(this);
    private SharedPreferences sp;
    private RelativeLayout mHistoryLayout;
    private ImageView mClearText;
    private LinearLayout mContentLayout;
    private ListView mResultLv;
    private MyBookDao myBookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData2HotList();
        initView2HotLayout();
        initData2HistoryList();
    }

    private void initData2HistoryList() {
        Set<String> historySet = sp.getStringSet("history", new HashSet<String>());
        if (historySet != null && historySet.size() > 0) {
            mHistoryLayout.setVisibility(View.VISIBLE);
            mLv.setVisibility(View.VISIBLE);
            final List<String> historyList = new ArrayList<>();
            for (String s : historySet) {
                historyList.add(s);
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, historyList);
            mLv.setAdapter(adapter);
            mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mInputWorld.setText(historyList.get(position));
                    presenter.start(historyList.get(position));
                    saveHistory(historyList.get(position));
                }
            });
        } else {
            mHistoryLayout.setVisibility(View.GONE);
            mLv.setVisibility(View.GONE);
        }
    }

    private void initData2HotList() {
        list.clear();
        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt(MyConstants.stringList.size());
            String s = MyConstants.stringList.get(randomNumber);
            if (!list.contains(s)) {
                list.add(s);
            } else {
                i--;
            }
        }
    }


    private void initView() {
        ImageView goBack = (ImageView) findViewById(R.id.go_back);
        mInputWorld = ((EditText) findViewById(R.id.search_et));
        TextView search = (TextView) findViewById(R.id.search_tv);
        TextView replace = (TextView) findViewById(R.id.replace);
        TextView clear = (TextView) findViewById(R.id.clear_history);
        mLv = ((ListView) findViewById(R.id.lv));
        mHistoryLayout = ((RelativeLayout) findViewById(R.id.history_layout));
        mHotLayout = ((FlexboxLayout) findViewById(R.id.hot_layout));
        sp = getSharedPreferences(MyConstants.SEARCH_SP, MODE_PRIVATE);
        myBookDao = ((MyApp) getApplication()).daoSession.getMyBookDao();
        mClearText = ((ImageView) findViewById(R.id.clear_text));
        mContentLayout = ((LinearLayout) findViewById(R.id.content_layout));
        mResultLv = ((ListView) findViewById(R.id.lv_search_result));

        goBack.setOnClickListener(this);
        search.setOnClickListener(this);
        replace.setOnClickListener(this);
        clear.setOnClickListener(this);
        mClearText.setOnClickListener(this);

        mInputWorld.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String result = s.toString();
                if (result != null && !"".equals(result)) {
                    mClearText.setVisibility(View.VISIBLE);
                } else {
                    mClearText.setVisibility(View.GONE);
                    mContentLayout.setVisibility(View.VISIBLE);
                    mResultLv.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back://返回上一个界面
                finish();
                break;
            case R.id.search_tv://搜索
            {
                String result = mInputWorld.getText().toString();
                if (result != null && !"".equals(result)) {
                    saveHistory(result);
                    presenter.start(result);
                } else {
                    Toast.makeText(SearchActivity.this, "请输入搜索词", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.replace://换一换
                initData2HotList();
                initView2HotLayout();
                break;
            case R.id.clear_history://清空历史
                sp.edit().putStringSet("history", new HashSet<String>()).commit();
                initData2HistoryList();
                break;
            case R.id.clear_text:
                mInputWorld.setText("");
                break;
        }
    }

    private void saveHistory(String result) {
        Set<String> set = sp.getStringSet("history", new HashSet<String>());
        if (!set.contains(result)) {
            set.add(result);
        }
        sp.edit().putStringSet("history", set).commit();
    }

    public void initView2HotLayout() {
        mHotLayout.removeAllViewsInLayout();
        for (String s : list) {
            TextView tv = new TextView(this);
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 25;
            lp.bottomMargin = 25;
            tv.setLayoutParams(lp);
            tv.setBackgroundResource(R.drawable.hot_layout_tv_bg);
            tv.setPadding(15, 15, 15, 15);
            tv.setText(s);
            mHotLayout.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = ((TextView) v).getText().toString();
                    mInputWorld.setText(text);
                    presenter.start(text);
                    saveHistory(text);
                }
            });
        }
    }

    @Override
    public void searchData(final List<BookBean> list) {
        if (list != null && list.size() > 0) {
            mContentLayout.setVisibility(View.GONE);
            mResultLv.setVisibility(View.VISIBLE);
            final ClassifyListLvAdapter adapter = new ClassifyListLvAdapter(list, this, true, myBookDao);
            mResultLv.setAdapter(adapter);
            adapter.setListener(new ClassifyListLvAdapter.OnListener() {
                @Override
                public void onAddImgClickListener(View v, int position) {
                    BookBean bookBean = list.get(position);
                    ImageView addImg = (ImageView) v;
                    List<MyBook> myBooks = myBookDao.queryBuilder()
                            .where(MyBookDao.Properties.UserName.eq(MyConstants.userName))
                            .where(MyBookDao.Properties.BookId.eq(bookBean.getBookId())).list();
                    if (myBooks != null && myBooks.size() > 0) {
                        myBookDao.delete(myBooks.get(0));
                        addImg.setImageResource(R.drawable.add_to);
                    } else {
                        myBookDao.save(new MyBook(null, bookBean.getName(), bookBean.getBookImg(), bookBean.getBookId(),
                                bookBean.getAuthor(), bookBean.getFrom(), bookBean.getDescription(),
                                bookBean.getCount(), bookBean.getfCount(), bookBean.getrCount(), bookBean.getClassId(), 0, 0, MyConstants.userName));
                        addImg.setImageResource(R.drawable.add_to_success);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
            mResultLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BookBean bookBean = list.get(position);
                    List<MyBook> myBooks = myBookDao.queryBuilder()
                            .where(MyBookDao.Properties.UserName.eq(MyConstants.userName))
                            .where(MyBookDao.Properties.BookId.eq(bookBean.getBookId())).list();
                    if (myBooks != null && myBooks.size() > 0) {
                        Intent intent = new Intent(SearchActivity.this, ReadActivity.class);
                        intent.putExtra(MyConstants.BOOK_ID, bookBean.getBookId());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SearchActivity.this, BookDetailsActivity.class);
                        intent.putExtra("bookBean", bookBean);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
