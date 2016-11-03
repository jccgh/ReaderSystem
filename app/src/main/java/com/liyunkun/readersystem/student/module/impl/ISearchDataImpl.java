package com.liyunkun.readersystem.student.module.impl;

import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.intf.ISearchData;
import com.liyunkun.readersystem.student.view.intf.OnSearchCallBack;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liyunkun on 2016/11/2 0002.
 */
public class ISearchDataImpl implements ISearchData {
    @Override
    public void getData(final OnSearchCallBack callBack) {
        final List<String> listString = new ArrayList<>();
        BmobQuery<BookBean> query = new BmobQuery<>();
        query.addWhereGreaterThan("count", 4500);
        query.findObjects(new FindListener<BookBean>() {
            @Override
            public void done(List<BookBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        for (BookBean bookBean : list) {
                            listString.add(bookBean.getAuthor());
                            listString.add(bookBean.getName());
                        }
                        callBack.onSuccessful(listString);
                    }
                }
            }
        });
    }
}
