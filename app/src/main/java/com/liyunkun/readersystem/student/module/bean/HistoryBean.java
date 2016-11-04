package com.liyunkun.readersystem.student.module.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liyunkun on 2016/11/4 0004.
 */
@Entity
public class HistoryBean {
    @Id
    private Long id;
    @Property
    private String userName;
    @Property
    private String history;
    @Generated(hash = 1338196091)
    public HistoryBean(Long id, String userName, String history) {
        this.id = id;
        this.userName = userName;
        this.history = history;
    }
    @Generated(hash = 48590348)
    public HistoryBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getHistory() {
        return this.history;
    }
    public void setHistory(String history) {
        this.history = history;
    }
}
