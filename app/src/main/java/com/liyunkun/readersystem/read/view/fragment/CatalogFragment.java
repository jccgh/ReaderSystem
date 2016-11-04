package com.liyunkun.readersystem.read.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liyunkun.readersystem.BaseFragment;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyunkun on 2016/11/4 0004.
 */
public class CatalogFragment extends BaseFragment {

    private ListView mLv;
    private String[] titles;
    private List<String> stringList;
    private ArrayAdapter adapter;
    private int bookId;

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public static CatalogFragment getInstance(int bookId) {
        CatalogFragment fragment = new CatalogFragment();
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
        View view = inflater.inflate(R.layout.catalog_fg_layout, container, false);
        initView(view);
        initData();
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stringList);
        mLv.setAdapter(adapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sp = getActivity().getSharedPreferences(MyConstants.READ_SP, Context.MODE_PRIVATE);
                if (MyConstants.order_mode == MyConstants.ASC) {
                    sp.edit().putInt(MyConstants.READ_POSITION+bookId, position).commit();
                } else if (MyConstants.order_mode == MyConstants.DESC) {
                    sp.edit().putInt(MyConstants.READ_POSITION+bookId, stringList.size() - 1 - position).commit();
                }
                getActivity().finish();
            }
        });
        return view;
    }

    private void initView(View view) {
        mLv = ((ListView) view.findViewById(R.id.lv));
    }

    private void initData() {
        stringList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            if (MyConstants.order_mode == MyConstants.ASC) {
                stringList.add("(" + i + ")" + titles[i]);
            } else {
                int count = titles.length - 1 - i;
                stringList.add("(" + count + ")" + titles[count]);
            }
        }
    }
}
