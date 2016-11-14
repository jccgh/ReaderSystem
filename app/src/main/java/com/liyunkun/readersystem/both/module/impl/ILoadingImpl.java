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
                R.drawable.book_default, R.drawable.book_default, R.drawable.book_default, R.drawable.book_default,
                R.drawable.book_default, R.drawable.book_default
        };
        return img;
    }
}
