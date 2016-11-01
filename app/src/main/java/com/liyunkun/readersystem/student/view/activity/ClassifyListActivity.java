package com.liyunkun.readersystem.student.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.MyApp;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;
import com.liyunkun.readersystem.both.module.bean.DaoSession;
import com.liyunkun.readersystem.both.module.bean.MyBook;
import com.liyunkun.readersystem.both.module.bean.MyBookDao;
import com.liyunkun.readersystem.student.presenter.ClassifyListPresenter;
import com.liyunkun.readersystem.student.view.adapter.ClassifyListLvAdapter;
import com.liyunkun.readersystem.student.view.intf.IClassifyListView;

import java.util.List;

/**
 * https://www.zhihu.com/question/30895291/answer/128498676
 */
public class ClassifyListActivity extends BaseActivity implements IClassifyListView {

    private BookClassBean bookClassBean;
    private ListView mLv;
    private ImageView mGoBack;
    private TextView mTitle;
    private ClassifyListPresenter presenter = new ClassifyListPresenter(this);
    private ClassifyListLvAdapter adapter;
    private TextView mBookShelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        initData2BCB();
        initView();
        presenter.start(bookClassBean.getClassId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start(bookClassBean.getClassId());
    }

    private void initView() {
        mLv = ((ListView) findViewById(R.id.lv));
        mGoBack = ((ImageView) findViewById(R.id.iv));
        mTitle = ((TextView) findViewById(R.id.tv));
        mBookShelf = ((TextView) findViewById(R.id.book_shelf));


        mTitle.setText(bookClassBean.getType());
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBookShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassifyListActivity.this, StudentHomeActivity.class));
            }
        });
    }

    private void initData2BCB() {
        Intent intent = getIntent();
        bookClassBean = (BookClassBean) intent.getSerializableExtra("bookClassBean");
    }

    @Override
    public void updateLv(final List<BookBean> list) {
        DaoSession daoSession = ((MyApp) getApplication()).daoSession;
        final MyBookDao myBookDao = daoSession.getMyBookDao();
        adapter = new ClassifyListLvAdapter(list, this,true,myBookDao);
        mLv.setAdapter(adapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClassifyListActivity.this, BookDetailsActivity.class);
                intent.putExtra("bookBean",list.get(position));
                startActivity(intent);
            }
        });
        adapter.setListener(new ClassifyListLvAdapter.OnListener() {
            @Override
            public void onAddImgClickListener(View v, int position) {
                BookBean bookBean = list.get(position);
                ImageView addImg = (ImageView) v;
                List<MyBook> myBooks = myBookDao.queryBuilder().where(MyBookDao.Properties.BookId.eq(bookBean.getBookId())).list();
                if (myBooks != null && myBooks.size() > 0) {
                    myBookDao.delete(myBooks.get(0));
                    addImg.setImageResource(R.drawable.add_to);
                } else {
                    myBookDao.save(new MyBook(null, bookBean.getName(), bookBean.getBookImg(), bookBean.getBookId(),
                            bookBean.getAuthor(), bookBean.getFrom(), bookBean.getDescription(),
                            bookBean.getCount(), bookBean.getfCount(), bookBean.getrCount(), bookBean.getClassId(), 0, 0));
                    addImg.setImageResource(R.drawable.add_to_success);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
