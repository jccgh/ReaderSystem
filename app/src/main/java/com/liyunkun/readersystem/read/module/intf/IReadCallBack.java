package com.liyunkun.readersystem.read.module.intf;

import com.liyunkun.readersystem.both.module.bean.PageBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public interface IReadCallBack {
    void onSuccessful(List<PageBean> list);

    void onFailed(String msg);
}
