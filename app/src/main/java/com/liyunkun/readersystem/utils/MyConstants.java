package com.liyunkun.readersystem.utils;

import com.liyunkun.readersystem.both.module.bean.BookClassBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/18 0018.
 * 该类用于存放应用使用到的常量的名
 */
public class MyConstants {
    public static final String FIRSTDOWN = "lastDown";
    public static final String ADMINISTRATOR = "管理员";
    public static final String STUDENT = "学生";
    public static final String REMEMBER = "remember";
    public static final String USER_NAME = "userName";
    public static final String MODE_NUMBER = "modeNumber";
    public static final String SEARCH_SP = "search";
    public static final String READ_SP = "read";
    public static final String READ_POSITION = "read_position";
    public static final String BOOK_ID = "bookId";
    public static final String BOOK_BEAN = "bookBean";
    public static final String MODE = "mode";
    public static final int BOOK_MODE = 0;
    public static final int LIST_MODE = 1;
    public static int mode = LIST_MODE;
    public static final int ASC = 0;
    public static final int DESC = 1;
    public static int order_mode = ASC;
    public static String userName = "";
    public static List<String> stringList;
    public static final float max_text_size = 40;
    public static final float min_text_size = 10;
    public static float default_text_size = 20;
    public static float default_line_height = 0.5F;
    public static float line_height_02 = 0.2F;
    public static float line_height_10 = 1.0F;
    public static float line_height_15 = 1.5F;
    public static float line_height = default_line_height;

    public static int default_reading_bg=0;
    public static int reading_bg=default_reading_bg;
    public static int reading_bg_1=1;//eye
    public static int reading_bg_2=2;//kraft
    public static int reading_bg_3=3;//night1
    public static int reading_bg_4=4;//night2
    public static int reading_bg_5=5;//powerless
    public static int reading_bg_6=6;//soft
    public static int reading_bg_7=7;//4
    public static int reading_bg_8=8;//5




    public static List<BookClassBean> list;

}
