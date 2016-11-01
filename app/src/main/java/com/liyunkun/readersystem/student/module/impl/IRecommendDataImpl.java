package com.liyunkun.readersystem.student.module.impl;

import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.intf.IRecommendData;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public class IRecommendDataImpl implements IRecommendData{
    @Override
    public void getData(final IBookCallBack callBack) {
        BmobQuery<BookBean> query=new BmobQuery<>();
        query.addWhereGreaterThan("count",3000);
        query.findObjects(new FindListener<BookBean>() {
            @Override
            public void done(List<BookBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        callBack.onSuccessful(list);
                    }
                }
            }
        });
    }
}
