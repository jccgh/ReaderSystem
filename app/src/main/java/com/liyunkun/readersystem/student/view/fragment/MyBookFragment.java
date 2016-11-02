package com.liyunkun.readersystem.student.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.liyunkun.readersystem.read.view.activity.ReadActivity;
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
    private List<MyBook> list;
    private MyBookFgRvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_book_fg_layout, container, false);
        initView(view);
        initData2BookList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData2BookList();
    }

    private void initData2BookList() {
        DaoSession daoSession = ((MyApp) getActivity().getApplication()).daoSession;
        myBookDao = daoSession.getMyBookDao();
        list = myBookDao.queryBuilder().list();
        if (list != null && list.size() > 0) {
            mIv.setVisibility(View.GONE);
            mRv.setVisibility(View.VISIBLE);
            if (MyConstants.BOOK_MODE == MyConstants.mode) {
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
                mRv.setLayoutManager(manager);
            } else if (MyConstants.LIST_MODE == MyConstants.mode) {
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                mRv.setLayoutManager(manager);
            }
            adapter = new MyBookFgRvAdapter(MyConstants.mode, list, getActivity());
            mRv.setAdapter(adapter);
            adapter.setOnItemViewListener(new MyBookFgRvAdapter.OnItemViewListener() {
                @Override
                public void onItemClickListener(View v, int position) {
                    Intent intent = new Intent(getActivity(), ReadActivity.class);
                    intent.putExtra("bookId", list.get(position).getBookId());
                    startActivity(intent);
                }

                @Override
                public void onItemLongClickListener(View v, final int position) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("提示")
                            .setMessage("是否要移除书架并删除文件")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MyBook myBook = list.get(position);
                                    myBookDao.delete(myBook);
                                    list.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("否", null);
                    builder.create().show();
                }
            });
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
