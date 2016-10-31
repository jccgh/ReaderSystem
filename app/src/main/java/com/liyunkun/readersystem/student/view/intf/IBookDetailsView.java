package com.liyunkun.readersystem.student.view.intf;

import com.liyunkun.readersystem.both.module.bean.BookBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public interface IBookDetailsView {
    void setData2LvType(List<BookBean> list);
    void setData2LvAuthor(List<BookBean> list);
}
