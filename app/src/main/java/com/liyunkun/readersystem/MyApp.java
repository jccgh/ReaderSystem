package com.liyunkun.readersystem;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

/**
 * Created by liyunkun on 2016/10/13 0013.
 * application类，用于初始化一些通用属性
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bmob
        Bmob.initialize(this, "0c0b50e4353a6dd5ad625b04006a3f6a");
        //初始化Fresco
        Fresco.initialize(this);
        initDataList();
    }

    private void initDataList() {
        MyConstants.list=new ArrayList<>();
        MyConstants.list.add(new BookClassBean(1,"孕妇育儿",50,"http://tnfs.tngou.net/image/book/150802/29c1f9364159b58d4542b7fe3d25e0fb.jpg"));
        MyConstants.list.add(new BookClassBean(2,"美容时尚",123,"http://tnfs.tngou.net/image/book/150802/56d9c90a6d1277b55eda37a73a40bca3.jpg"));
        MyConstants.list.add(new BookClassBean(3,"健康养生",234,"http://tnfs.tngou.net/image/book/150802/419b33c4da8687539ab4ef2eb3e1ccd3.jpg"));
        MyConstants.list.add(new BookClassBean(4,"两性生活",56,"http://tnfs.tngou.net/image/book/150802/c9e6346d7eb6fa4a40d818754e0e4858.jpg"));
        MyConstants.list.add(new BookClassBean(5,"美食烹饪",42,"http://tnfs.tngou.net/image/book/150802/c6c2614b41f4933051946ca74bd01f31.jpg"));
        MyConstants.list.add(new BookClassBean(6,"修养心里",76,"http://tnfs.tngou.net/image/book/150802/ae02caf84ef5140e4bd81db044cf487a.jpg"));
        MyConstants.list.add(new BookClassBean(7,"家庭教育",21,"http://tnfs.tngou.net/image/book/150802/dc2b33f0b6d18c75c111a4361f80ae22.jpg"));
        MyConstants.list.add(new BookClassBean(8,"幽默笑话",98,"http://tnfs.tngou.net/image/book/150802/50ce000a8f8aeaba7c03ccf26ff419b0.jpg"));
        MyConstants.list.add(new BookClassBean(9,"生活杂谈",100,"http://tnfs.tngou.net/image/book/150802/3f384e9c416dd5ffa8330e6be6d9cbfb.jpg"));
        MyConstants.list.add(new BookClassBean(10,"其它",55,"http://tnfs.tngou.net/image/book/150802/b09680bea4e81d7e068c0c9d001f0809.jpg"));
    }
}
