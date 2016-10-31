package com.liyunkun.readersystem.administrator.view.intf;

import com.liyunkun.readersystem.both.module.bean.BookBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public interface IAdminView {
    void saveData2Book(List<BookBean> list);
}
