package com.liyunkun.readersystem.student.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyunkun.readersystem.BaseFragment;

/**
 * Created by liyunkun on 2016/10/15 0015.
 */
public class MyBookFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv=new TextView(getActivity());
        tv.setText("MyBookFragment");
        return tv;
    }
}
