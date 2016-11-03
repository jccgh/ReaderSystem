package com.liyunkun.readersystem.utils;

import com.liyunkun.readersystem.both.module.bean.BookClassBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/18 0018.
 * 该类用于存放应用使用到的常量的名
 */
public class MyConstants {
    public static final String FIRSTDOWN="lastDown";
    public static final String ADMINISTRATOR="管理员";
    public static final String STUDENT="学生";
    public static final String REMEMBER="remember";
    public static final String USER_NAME="userName";
    public static final String MODE_NUMBER="modeNumber";
    public static final String SEARCH_SP="search";
    public static final String BOOK_ID="bookId";
    public static final String MODE="mode";
    public static final int BOOK_MODE=0;
    public static final int LIST_MODE=1;
    public static int mode = LIST_MODE;
    public static  String userName="";
    public static List<String> stringList;

    public static List<BookClassBean> list;

}
