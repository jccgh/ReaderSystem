package com.liyunkun.readersystem.read.module.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liyunkun on 2016/11/4 0004.
 */
@Entity
public class Note {
    @Property
    private String createTime;
    @Id
    private Long id;
    @Property
    private String content;
    @Property
    private String pageTitle;
    @Property
    private int pageId;
    @Property
    private String userName;
    @Property
    private int bookId;
    @Generated(hash = 601022410)
    public Note(String createTime, Long id, String content, String pageTitle,
            int pageId, String userName, int bookId) {
        this.createTime = createTime;
        this.id = id;
        this.content = content;
        this.pageTitle = pageTitle;
        this.pageId = pageId;
        this.userName = userName;
        this.bookId = bookId;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPageTitle() {
        return this.pageTitle;
    }
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
    public int getPageId() {
        return this.pageId;
    }
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getBookId() {
        return this.bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
