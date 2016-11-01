package com.liyunkun.readersystem.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by liyunkun on 2016/10/29 0029.
 */
public class NetUtils {
    public static String bookListUrl="http://www.tngou.net/api/book/list?id=%d&rows=%d";
    public static String bookContentsUrl="http://www.tngou.net/api/book/show?id=%d";
    public static OkHttpClient client=new OkHttpClient.Builder()
            .connectTimeout(5*1000, TimeUnit.SECONDS)
            .build();
}
