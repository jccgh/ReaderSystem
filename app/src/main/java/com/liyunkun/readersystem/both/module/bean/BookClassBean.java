package com.liyunkun.readersystem.both.module.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by liyunkun on 2016/10/29 0029.
 */
public class BookClassBean extends BmobObject implements Serializable{
    private int classId;//分类id
    private String type;//类型
    private int totalBook;//图书总数
    private String img;//图片

    public BookClassBean() {
    }

    public BookClassBean(int classId, String type, int totalBook, String img) {
        this.classId = classId;
        this.type = type;
        this.totalBook = totalBook;
        this.img = img;
    }

    public int getTotalBook() {
        return totalBook;
    }

    public void setTotalBook(int totalBook) {
        this.totalBook = totalBook;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
