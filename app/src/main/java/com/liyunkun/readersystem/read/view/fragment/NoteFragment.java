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
import com.liyunkun.readersystem.read.module.bean.Note;
import com.liyunkun.readersystem.read.module.bean.NoteDao;
import com.liyunkun.readersystem.read.view.adapter.MyAdapter;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/4 0004.
 */
public class NoteFragment extends BaseFragment {

    private LinearLayout mLayout;
    private ListView mLv;
    private int bookId;
    private NoteDao noteDao;

    public static NoteFragment getInstance(int bookId) {
        NoteFragment fragment = new NoteFragment();
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
        View view = inflater.inflate(R.layout.note_fg_layout, container, false);
        initView(view);
        initData2Lv();
        return view;
    }

    private void initData2Lv() {
        List<Note> list = noteDao.queryBuilder()
                .where(NoteDao.Properties.UserName.eq(MyConstants.userName))
                .where(NoteDao.Properties.BookId.eq(bookId))
                .list();
        if (list != null && list.size() > 0) {
            hideLayout();
            MyAdapter<Note> adapter=new MyAdapter<>(list,getActivity(),"note");
            mLv.setAdapter(adapter);
        } else {
            showLayout();
        }
    }

    private void initView(View view) {
        mLayout = ((LinearLayout) view.findViewById(R.id.layout));
        mLv = ((ListView) view.findViewById(R.id.lv));
        noteDao = ((MyApp) getActivity().getApplication()).daoSession.getNoteDao();
    }

    private void showLayout() {
        mLayout.setVisibility(View.VISIBLE);
        mLv.setVisibility(View.GONE);
    }

    private void hideLayout() {
        mLayout.setVisibility(View.GONE);
        mLv.setVisibility(View.VISIBLE);
    }
}
