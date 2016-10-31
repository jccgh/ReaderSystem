package com.liyunkun.readersystem.student.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.MyApp;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;
import com.liyunkun.readersystem.both.module.bean.DaoSession;
import com.liyunkun.readersystem.both.module.bean.MyBook;
import com.liyunkun.readersystem.both.module.bean.MyBookDao;
import com.liyunkun.readersystem.both.module.bean.MyFavorite;
import com.liyunkun.readersystem.both.module.bean.MyFavoriteDao;
import com.liyunkun.readersystem.student.presenter.BookDetailsPresenter;
import com.liyunkun.readersystem.student.view.MyListView;
import com.liyunkun.readersystem.student.view.adapter.ClassifyListLvAdapter;
import com.liyunkun.readersystem.student.view.intf.IBookDetailsView;
import com.liyunkun.readersystem.utils.MyConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookDetailsActivity extends BaseActivity implements View.OnClickListener, IBookDetailsView {

    private BookBean bookBean;
    private ImageView mBookImg;
    private TextView mBookName;
    private TextView mBookName2;
    private TextView mAuthor;
    private TextView mAuthor2;
    private TextView mAuthor3;
    private TextView mContents;
    private TextView mClassName;
    private TextView mClassName2;
    private TextView mFrom;
    private ImageView mGoBack;
    private MyListView mLvType;
    private MyListView mLvAuthor;
    private BookDetailsPresenter presenter = new BookDetailsPresenter(this);
    private LinearLayout mFavoriteLayout;
    private ImageView mFavoriteImg;
    private TextView mFavoriteTv;
    private MyFavoriteDao myFavoriteDao;
    private DaoSession daoSession;
    private MyBookDao myBookDao;
    private ImageView mMyBookImg;
    private TextView mMyBookTv;
    private LinearLayout mMyBookLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        initData2BookBean();
        initView();
        initFavorite();
        initBook();
        setData2ClassName();
        presenter.start(bookBean.getName(), bookBean.getClassId());
        presenter.start(bookBean.getName(), bookBean.getAuthor());
    }

    private void initBook() {
        myBookDao = daoSession.getMyBookDao();
        List<MyBook> list = myBookDao.queryBuilder().where(MyBookDao.Properties.BookId.eq(bookBean.getBookId())).list();
        if (list != null && list.size() > 0) {
            mMyBookImg.setImageResource(R.mipmap.ic_launcher);
            mMyBookTv.setText("移除书架");
        } else {
            mMyBookImg.setImageResource(R.mipmap.ic_launcher);
            mMyBookTv.setText("加入书架");
        }
    }

    private void initFavorite() {
        daoSession = ((MyApp) getApplication()).daoSession;
        myFavoriteDao = daoSession.getMyFavoriteDao();
        List<MyFavorite> list = myFavoriteDao.queryBuilder().where(MyFavoriteDao.Properties.BookId.eq(bookBean.getBookId())).list();
        if (list != null && list.size() > 0) {
            mFavoriteImg.setImageResource(R.mipmap.ic_launcher);
            mFavoriteTv.setText("取消收藏");
        } else {
            mFavoriteImg.setImageResource(R.mipmap.ic_launcher);
            mFavoriteTv.setText("收藏");
        }
    }

    private void setData2ClassName() {
        String type = null;
        List<BookClassBean> bookClassBeen = MyConstants.list;
        for (int i = 0; i < bookClassBeen.size(); i++) {
            if (bookBean.getClassId() == bookClassBeen.get(i).getClassId()) {
                type = bookClassBeen.get(i).getType();
                break;
            }
        }
        if (type != null) {
            mClassName.setText("图书分类： " + type);
            mClassName2.setText(type + "  的小说还有……");
        }

    }

    private void initView() {
        mBookImg = ((ImageView) findViewById(R.id.book_img));
        mBookName = ((TextView) findViewById(R.id.book_name));
        mBookName2 = ((TextView) findViewById(R.id.book_name2));
        mAuthor = ((TextView) findViewById(R.id.author));
        mAuthor2 = ((TextView) findViewById(R.id.author2));
        mAuthor3 = ((TextView) findViewById(R.id.author3));
        mContents = ((TextView) findViewById(R.id.content));
        mClassName = ((TextView) findViewById(R.id.class_name));
        mClassName2 = ((TextView) findViewById(R.id.class_name2));
        mFrom = ((TextView) findViewById(R.id.from));
        mGoBack = ((ImageView) findViewById(R.id.go_back));
        mLvType = ((MyListView) findViewById(R.id.lv_type));
        mLvAuthor = ((MyListView) findViewById(R.id.lv_author));
        mFavoriteLayout = ((LinearLayout) findViewById(R.id.favorite_layout));
        mFavoriteImg = ((ImageView) findViewById(R.id.favorite_img));
        mFavoriteTv = ((TextView) findViewById(R.id.favorite_tv));
        mMyBookImg = ((ImageView) findViewById(R.id.my_book_img));
        mMyBookTv = ((TextView) findViewById(R.id.my_book_tv));
        mMyBookLayout = ((LinearLayout) findViewById(R.id.my_book_layout));


        Picasso.with(this).load(bookBean.getBookImg()).into(mBookImg);
        mBookName.setText(bookBean.getName());
        mBookName2.setText("书名： " + bookBean.getName());
        mAuthor.setText("作者： " + bookBean.getAuthor());
        mAuthor2.setText("作者： " + bookBean.getAuthor());
        mContents.setText(bookBean.getDescription());
        String from = bookBean.getFrom();
        if (from != null && "".equals(from)) {
            mFrom.setText("出版社： " + from);
        } else {
            mFrom.setText("出版社： " + "");
        }

        mFavoriteLayout.setOnClickListener(this);
        mGoBack.setOnClickListener(this);
        mMyBookLayout.setOnClickListener(this);
    }

    private void initData2BookBean() {
        Intent intent = getIntent();
        bookBean = (BookBean) intent.getSerializableExtra("bookBean");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.favorite_layout:
                updateFavorite();
                break;
            case R.id.my_book_layout:
                updateBook();
                break;
        }
    }

    private void updateBook() {
        if ("加入书架".equals(mMyBookTv.getText().toString())) {
            myBookDao.save(new MyBook(null, bookBean.getName(), bookBean.getBookImg(), bookBean.getBookId(),
                    bookBean.getAuthor(), bookBean.getFrom(), bookBean.getDescription(),
                    bookBean.getCount(), bookBean.getfCount(), bookBean.getrCount(), bookBean.getClassId(), 0, 0));
            mMyBookImg.setImageResource(R.mipmap.ic_launcher);
            mMyBookTv.setText("移除书架");

        } else if ("移除书架".equals(mMyBookTv.getText().toString())) {
            List<MyBook> list = myBookDao.queryBuilder().where(MyBookDao.Properties.BookId.eq(bookBean.getBookId())).list();
            if (list != null && list.size() > 0) {
                myBookDao.delete(list.get(0));
                mMyBookImg.setImageResource(R.mipmap.ic_launcher);
                mMyBookTv.setText("加入书架");
            }
        }
    }

    private void updateFavorite() {
        if ("收藏".equals(mFavoriteTv.getText().toString())) {
            myFavoriteDao.save(new MyFavorite(null, bookBean.getName(), bookBean.getBookImg(), bookBean.getBookId(),
                    bookBean.getAuthor(), bookBean.getFrom(), bookBean.getDescription(),
                    bookBean.getCount(), bookBean.getfCount(), bookBean.getrCount(), bookBean.getClassId()));
            mFavoriteImg.setImageResource(R.mipmap.ic_launcher);
            mFavoriteTv.setText("取消收藏");
        } else if ("取消收藏".equals(mFavoriteTv.getText().toString())) {
            List<MyFavorite> list = myFavoriteDao.queryBuilder().where(MyFavoriteDao.Properties.BookId.eq(bookBean.getBookId())).list();
            if (list != null && list.size() > 0) {
                myFavoriteDao.delete(list.get(0));
                mFavoriteImg.setImageResource(R.mipmap.ic_launcher);
                mFavoriteTv.setText("收藏");
            }
        }
    }

    @Override
    public void setData2LvType(final List<BookBean> list) {
        if (list != null) {
            ClassifyListLvAdapter adapter = new ClassifyListLvAdapter(list, this, false);
            mLvType.setAdapter(adapter);
            mLvType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(BookDetailsActivity.this, BookDetailsActivity.class);
                    intent.putExtra("bookBean", list.get(position));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void setData2LvAuthor(final List<BookBean> list) {
        if (list != null) {
            mAuthor3.setText(bookBean.getAuthor() + "  还写过……");
            ClassifyListLvAdapter adapter = new ClassifyListLvAdapter(list, this, false);
            mLvAuthor.setAdapter(adapter);
            mLvAuthor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(BookDetailsActivity.this, BookDetailsActivity.class);
                    intent.putExtra("bookBean", list.get(position));
                    startActivity(intent);
                }
            });
        }
    }
}
