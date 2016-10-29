package com.liyunkun.readersystem.student.module.bean;

import com.liyunkun.readersystem.both.module.bean.BookBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/28 0028.
 */
public class BookShopLvBean {

    private String title;
    private List<BookBean> list;

    public BookShopLvBean() {
    }

    public BookShopLvBean(String title, List<BookBean> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BookBean> getList() {
        return list;
    }

    public void setList(List<BookBean> list) {
        this.list = list;
    }
}
