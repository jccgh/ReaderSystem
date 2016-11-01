package com.liyunkun.readersystem.student.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liyunkun.readersystem.BaseFragment;
import com.liyunkun.readersystem.MyApp;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.DaoSession;
import com.liyunkun.readersystem.both.module.bean.MyBook;
import com.liyunkun.readersystem.both.module.bean.MyBookDao;
import com.liyunkun.readersystem.student.view.adapter.MyBookFgRvAdapter;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/15 0015.
 */
public class MyBookFragment extends BaseFragment {

    private ImageView mIv;
    private RecyclerView mRv;
    private MyBookDao myBookDao;
    private int mode = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_book_fg_layout, container, false);
        initView(view);
        initData2BookList();
        return view;
    }

    private void initData2BookList() {
        DaoSession daoSession = ((MyApp) getActivity().getApplication()).daoSession;
        myBookDao = daoSession.getMyBookDao();
        List<MyBook> list = myBookDao.queryBuilder().list();
        if (list != null && list.size() > 0) {
            mIv.setVisibility(View.GONE);
            mRv.setVisibility(View.VISIBLE);
            if (MyConstants.BOOK_MODE == mode) {
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
                mRv.setLayoutManager(manager);
            } else if (MyConstants.LIST_MODE == mode) {
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                mRv.setLayoutManager(manager);
            }
            MyBookFgRvAdapter adapter = new MyBookFgRvAdapter(mode, list, getActivity());
            mRv.setAdapter(adapter);
        } else {
            mIv.setVisibility(View.VISIBLE);
            mRv.setVisibility(View.GONE);
        }
    }

    private void initView(View view) {
        mIv = ((ImageView) view.findViewById(R.id.iv));
        mRv = ((RecyclerView) view.findViewById(R.id.rv));
    }
}
