package com.liyunkun.readersystem.both.module.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liyunkun on 2016/10/13 0013.
 * 用户登录的数据
 */
public class UserBean extends BmobObject {
    private String type;//类型
    private String userName;//用户名
    private String password;//密码

    public UserBean() {
    }

    public UserBean(String type, String userName, String password) {
        this.type = type;
        this.userName = userName;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
