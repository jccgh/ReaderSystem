package com.liyunkun.readersystem.both.module.impl;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.inf.ILoading;

/**
 * Created by liyunkun on 2016/10/13 0013.
 * ILoading的实现类
 * 引导界面的module接口的实现类
 */
public class ILoadingImpl implements ILoading {
    /**
     * 获取资源数据
     * 返回资源数据的int数组
     *
     * @return
     */
    @Override
    public int[] getData() {
        int[] img = new int[]{
                R.drawable.btn_mark_1, R.drawable.btn_mark_2, R.drawable.btn_mark_3, R.drawable.btn_mark_4,
                R.drawable.btn_mark_5, R.drawable.btn_mark_6
        };
        return img;
    }
}
