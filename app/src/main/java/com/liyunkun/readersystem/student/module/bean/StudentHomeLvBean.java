package com.liyunkun.readersystem.student.module.bean;

/**
 * Created by liyunkun on 2016/10/15 0015.
 */
public class StudentHomeLvBean {
    private int img;
    private String content;
    private int type;
    private int[] imgs;
    private String[] contents;

    public StudentHomeLvBean() {
    }

    public StudentHomeLvBean(int img, String content, int type, int[] imgs, String[] contents) {
        this.img = img;
        this.content = content;
        this.type = type;
        this.imgs = imgs;
        this.contents = contents;
    }

    public int[] getImgs() {
        return imgs;
    }

    public void setImgs(int[] imgs) {
        this.imgs = imgs;
    }

    public String[] getContents() {
        return contents;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
