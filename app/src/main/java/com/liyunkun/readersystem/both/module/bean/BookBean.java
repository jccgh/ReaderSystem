package com.liyunkun.readersystem.both.module.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by liyunkun on 2016/10/19 0019.
 */
public class BookBean extends BmobObject implements Serializable{

    private String name;//书名
    private String bookImg;//图书图片的url
    private int BookId;//图书的id号
    private String author;//图书的作者
    private String from;//出版社
    private String description;//简介
    private int count;//阅读次数
    private int fCount;//收藏数
    private int rCount;//评论读数
    private int classId;//分类ID

    public BookBean() {
    }

    public BookBean(String name, String bookImg, int bookId, String author, String from, String description, int count, int fCount, int rCount, int classId) {
        this.name = name;
        this.bookImg = bookImg;
        BookId = bookId;
        this.author = author;
        this.from = from;
        this.description = description;
        this.count = count;
        this.fCount = fCount;
        this.rCount = rCount;
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getfCount() {
        return fCount;
    }

    public void setfCount(int fCount) {
        this.fCount = fCount;
    }

    public int getrCount() {
        return rCount;
    }

    public void setrCount(int rCount) {
        this.rCount = rCount;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
