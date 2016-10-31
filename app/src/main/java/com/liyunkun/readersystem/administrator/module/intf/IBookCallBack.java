package com.liyunkun.readersystem.administrator.module.intf;

import com.liyunkun.readersystem.both.module.bean.BookBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public interface IBookCallBack {
    void onFailed(String errorMsg);

    void onSuccessful(List<BookBean> list);
}
