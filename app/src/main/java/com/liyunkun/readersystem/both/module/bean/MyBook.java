package com.liyunkun.readersystem.both.module.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
@Entity
public class MyBook {
    @Id
    private Long id;
    @Property
    private String name;//书名
    @Property
    private String bookImg;//图书图片的url
    @Property
    private int BookId;//图书的id号
    @Property
    private String author;//图书的作者
    @Property
    private String from;//出版社
    @Property
    private String description;//简介
    @Property
    private int count;//阅读次数
    @Property
    private int fCount;//收藏数
    @Property
    private int rCount;//评论读数
    @Property
    private int classId;//分类ID
    @Property
    private int totalPage;//总的章数
    @Property
    private int currentPage;//当前章数

    @Generated(hash = 1928624497)
    public MyBook(Long id, String name, String bookImg, int BookId, String author,
                  String from, String description, int count, int fCount, int rCount,
                  int classId, int totalPage, int currentPage) {
        this.id = id;
        this.name = name;
        this.bookImg = bookImg;
        this.BookId = BookId;
        this.author = author;
        this.from = from;
        this.description = description;
        this.count = count;
        this.fCount = fCount;
        this.rCount = rCount;
        this.classId = classId;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    @Generated(hash = 794154407)
    public MyBook() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookImg() {
        return this.bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public int getBookId() {
        return this.BookId;
    }

    public void setBookId(int BookId) {
        this.BookId = BookId;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFCount() {
        return this.fCount;
    }

    public void setFCount(int fCount) {
        this.fCount = fCount;
    }

    public int getRCount() {
        return this.rCount;
    }

    public void setRCount(int rCount) {
        this.rCount = rCount;
    }

    public int getClassId() {
        return this.classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
