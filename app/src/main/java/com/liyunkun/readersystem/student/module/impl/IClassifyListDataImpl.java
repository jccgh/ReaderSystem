package com.liyunkun.readersystem.student.module.impl;

import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.intf.IClassifyListData;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public class IClassifyListDataImpl implements IClassifyListData {
    @Override
    public void getData(int classId, final IBookCallBack callBack) {
        BmobQuery<BookBean> query=new BmobQuery<>();
        query.addWhereEqualTo("classId",classId);
        query.findObjects(new FindListener<BookBean>() {
            @Override
            public void done(List<BookBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        callBack.onSuccessful(list);
                    } else {
                        callBack.onFailed("网络不可用");
                    }
                }else {
                    callBack.onFailed("网络不可用");
                }
            }
        });
    }
}
