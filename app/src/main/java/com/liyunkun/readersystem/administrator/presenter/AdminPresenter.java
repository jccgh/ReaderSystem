package com.liyunkun.readersystem.administrator.presenter;

import android.os.Handler;

import com.liyunkun.readersystem.BasePresenter;
import com.liyunkun.readersystem.administrator.module.impl.IAdminDataImpl;
import com.liyunkun.readersystem.administrator.module.intf.IAdminData;
import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.administrator.view.intf.IAdminView;
import com.liyunkun.readersystem.both.module.bean.BookBean;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public class AdminPresenter implements BasePresenter{
    private IAdminView view;
    private IAdminData data;
    private Handler mHandler=new Handler();

    public AdminPresenter(IAdminView view) {
        this.view = view;
        data=new IAdminDataImpl();
    }

    public void start(int classId, int totalCount) {
        data.saveData2Book(classId, totalCount, new IBookCallBack() {
            @Override
            public void onFailed(String errorMsg) {

            }

            @Override
            public void onSuccessful(final List<BookBean> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.saveData2Book(list);
                    }
                });
            }
        });
    }
    @Override
    public void start() {

    }
}
