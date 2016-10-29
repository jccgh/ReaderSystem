package com.liyunkun.readersystem.student.module.bean;

/**
 * Created by liyunkun on 2016/10/18 0018.
 */
public class StudentPwLvBean {

    private int img;
    private String content;

    public StudentPwLvBean() {
    }

    public StudentPwLvBean(int img, String content) {
        this.img = img;
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
