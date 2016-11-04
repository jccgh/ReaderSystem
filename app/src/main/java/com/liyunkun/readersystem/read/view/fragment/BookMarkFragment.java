package com.liyunkun.readersystem.read.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.liyunkun.readersystem.BaseFragment;
import com.liyunkun.readersystem.MyApp;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.read.module.bean.BookMark;
import com.liyunkun.readersystem.read.module.bean.BookMarkDao;
import com.liyunkun.readersystem.read.view.adapter.MyAdapter;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/4 0004.
 */
public class BookMarkFragment extends BaseFragment {

    private LinearLayout mLayout;
    private ListView mLv;
    private BookMarkDao bookMarkDao;
    private int bookId;

    public static BookMarkFragment getInstance(int bookId) {
        BookMarkFragment fragment = new BookMarkFragment();
        Bundle args = new Bundle();
        args.putInt("bookId", bookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        bookId = bundle.getInt("bookId");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_mark_fg_layout, container, false);
        initView(view);
        initData2Lv();
        return view;
    }

    private void initData2Lv() {
        List<BookMark> list = bookMarkDao.queryBuilder()
                .where(BookMarkDao.Properties.UserName.eq(MyConstants.userName))
                .where(BookMarkDao.Properties.BookId.eq(bookId))
                .list();
        if (list != null && list.size() > 0) {
            hideLayout();
            MyAdapter<BookMark> adapter = new MyAdapter<>(list, getActivity(), "mark");
            mLv.setAdapter(adapter);
        } else {
            showLayout();
        }
    }

    private void showLayout() {
        mLayout.setVisibility(View.VISIBLE);
        mLv.setVisibility(View.GONE);
    }

    private void hideLayout() {
        mLayout.setVisibility(View.GONE);
        mLv.setVisibility(View.VISIBLE);
    }

    private void initView(View view) {
        mLayout = ((LinearLayout) view.findViewById(R.id.layout));
        mLv = ((ListView) view.findViewById(R.id.lv));
        bookMarkDao = ((MyApp) getActivity().getApplication()).daoSession.getBookMarkDao();
    }
}
