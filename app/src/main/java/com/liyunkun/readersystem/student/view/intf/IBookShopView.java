package com.liyunkun.readersystem.student.view.intf;

import com.liyunkun.readersystem.student.module.bean.BookShopLvBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/28 0028.
 */
public interface IBookShopView {
    void initData2Lv(List<BookShopLvBean> list);
    void initData2Vp(List<String> list);
}
