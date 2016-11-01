package com.liyunkun.readersystem.read.presenter;

import android.os.Handler;

import com.liyunkun.readersystem.both.module.bean.PageBean;
import com.liyunkun.readersystem.read.module.impl.IReadDataImpl;
import com.liyunkun.readersystem.read.module.intf.IReadCallBack;
import com.liyunkun.readersystem.read.module.intf.IReadData;
import com.liyunkun.readersystem.read.view.intf.IReadView;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public class ReadPresenter {
    private IReadData data;
    private IReadView view;
    private Handler mHandler=new Handler();

    public ReadPresenter(IReadView view) {
        this.view = view;
        data=new IReadDataImpl();
    }
    public void start(int bookId){
        data.getData(bookId, new IReadCallBack() {
            @Override
            public void onSuccessful(final List<PageBean> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.updateRv(list);
                    }
                });
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }
}
