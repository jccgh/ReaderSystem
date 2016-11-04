package com.liyunkun.readersystem.read.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catalog_fg_layout, container, false);
        initView(view);
        initData();
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,stringList);
        mLv.setAdapter(adapter);
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
    public void updateData2Lv(){
        initData();
        adapter.notifyDataSetChanged();
    }
}
