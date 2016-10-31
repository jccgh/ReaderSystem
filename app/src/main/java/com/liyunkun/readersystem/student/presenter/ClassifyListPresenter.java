package com.liyunkun.readersystem.student.presenter;

import android.os.Handler;

import com.liyunkun.readersystem.BasePresenter;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.impl.IClassifyListDataImpl;
import com.liyunkun.readersystem.student.module.intf.IBookCallBack;
import com.liyunkun.readersystem.student.module.intf.IClassifyListData;
import com.liyunkun.readersystem.student.view.intf.IClassifyListView;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public class ClassifyListPresenter implements BasePresenter{
    private IClassifyListData data;
    private IClassifyListView view;
    private Handler mHandler=new Handler();

    public ClassifyListPresenter(IClassifyListView view) {
        this.view = view;
        data=new IClassifyListDataImpl();
    }

    public void start(int classId,int totalCount){
        data.getData(classId, totalCount, new IBookCallBack() {
            @Override
            public void onFailed(String errorMsg) {

            }

            @Override
            public void onSuccessful(final List<BookBean> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.updateLv(list);
                    }
                });
            }
        });
    }
    @Override
    public void start() {

    }
}
