package com.liyunkun.readersystem.both.module.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liyunkun on 2016/10/29 0029.
 */
public class PageBean extends BmobObject {
    private int pageId;
    private String message;
    private String title;
    private int bookClassId;
    private int bookId;

    public PageBean() {

    }

    public PageBean(int pageId, String message, String title, int bookClassId, int bookId) {
        this.pageId = pageId;
        this.message = message;
        this.title = title;
        this.bookClassId = bookClassId;
        this.bookId = bookId;
    }

    public int getBookClassId() {
        return bookClassId;
    }

    public void setBookClassId(int bookClassId) {
        this.bookClassId = bookClassId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
