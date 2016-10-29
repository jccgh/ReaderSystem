package com.liyunkun.readersystem.student.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

;

/**
 * Created by liyunkun on 2016/10/15 0015.
 */
public class StudentHomeVpAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;

    public StudentHomeVpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
