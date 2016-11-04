package com.liyunkun.readersystem.student.module.impl;

import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;
import com.liyunkun.readersystem.student.module.intf.ISearchData;
import com.liyunkun.readersystem.utils.MyConstants;

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
    public void getData2Result(String searchWorld, final IBookCallBack callBack) {
        BmobQuery<BookBean> query1 = new BmobQuery<>();
//        query1.addWhereContains("name", searchWorld);
        query1.addWhereEqualTo("name", searchWorld);
        BmobQuery<BookBean> query2 = new BmobQuery<>();
//        query2.addWhereContains("author", searchWorld);
        query2.addWhereEqualTo("author", searchWorld);
        List<BookClassBean> list = MyConstants.list;
        List<BmobQuery<BookBean>> listQuery = new ArrayList<>();
        listQuery.add(query1);
        listQuery.add(query2);
        int classId = -1;
        for (int i = 0; i < list.size(); i++) {
            if (searchWorld.equals(list.get(i).getType())) {
                classId = list.get(i).getClassId();
                break;
            }
        }
        if (classId != -1) {
            BmobQuery<BookBean> query3 = new BmobQuery<>();
            query3.addWhereEqualTo("classId", classId);
            listQuery.add(query3);
        }
        BmobQuery<BookBean> mainQuery = new BmobQuery<>();
        mainQuery.or(listQuery);
        mainQuery.findObjects(new FindListener<BookBean>() {
            @Override
            public void done(List<BookBean> list, BmobException e) {
                if (e == null) {
                    callBack.onSuccessful(list);
                }
            }
        });
    }
}
