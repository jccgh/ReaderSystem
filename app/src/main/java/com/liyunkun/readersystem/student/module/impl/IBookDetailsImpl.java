package com.liyunkun.readersystem.student.module.impl;

import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.intf.IBookDetailsData;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public class IBookDetailsImpl implements IBookDetailsData {

    @Override
    public void getData2LvType(final String bookName, int classId, final IBookCallBack callBack) {
        BmobQuery<BookBean> query = new BmobQuery<>();
        query.addWhereEqualTo("classId", classId);
        query.findObjects(new FindListener<BookBean>() {
            @Override
            public void done(List<BookBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        List<BookBean> bookBeen = new ArrayList<BookBean>();
                        int i = 0;
                        while (true) {
                            if (!bookName.equals(list.get(i).getName())) {
                                bookBeen.add(list.get(i));
                            }
                            i++;
                            if (bookBeen.size() == 5) {
                                break;
                            }
                        }
                        callBack.onSuccessful(bookBeen);
                    }
                }
            }
        });
    }

    @Override
    public void getData2LvAuthor(final String bookName, String author, final IBookCallBack callBack) {
        BmobQuery<BookBean> query = new BmobQuery<>();
        query.addWhereEqualTo("author", author);
        query.findObjects(new FindListener<BookBean>() {
            @Override
            public void done(List<BookBean> list, BmobException e) {
                if (e == null) {
                    int size = list.size();
                    if (list != null && size > 0) {
                        List<BookBean> bookBeen = new ArrayList<BookBean>();
                        int count = 5;
                        count = Math.min(size - 1, count);
                        int i = 0;
                        while (true) {
                            if (!bookName.equals(list.get(i).getName())) {
                                bookBeen.add(list.get(i));
                            }
                            i++;
                            if (bookBeen.size() == count) {
                                break;
                            }
                        }
                        callBack.onSuccessful(bookBeen);
                    }
                }
            }
        });
    }
}
