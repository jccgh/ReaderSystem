package com.liyunkun.readersystem.student.module.bean;

/**
 * Created by liyunkun on 2016/10/28 0028.
 */
public class RankLvBean {
    private int imgId;
    private String contents;

    public RankLvBean() {
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public RankLvBean(int imgId, String contents) {
        this.imgId = imgId;
        this.contents = contents;
    }
}
