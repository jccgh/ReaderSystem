package com.liyunkun.readersystem.student.presenter;

import com.liyunkun.readersystem.BasePresenter;
import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.impl.INewBookDataImpl;
import com.liyunkun.readersystem.student.module.intf.INewBookData;
import com.liyunkun.readersystem.student.view.intf.INewBookView;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public class NewBookPresenter implements BasePresenter{
    private INewBookData data;
    private INewBookView view;

    public NewBookPresenter(INewBookView view) {
        this.view = view;
        data=new INewBookDataImpl();
    }

    @Override
    public void start() {
        data.getData(new IBookCallBack() {
            @Override
            public void onFailed(String errorMsg) {

            }

            @Override
            public void onSuccessful(List<BookBean> list) {
                view.updateData2Lv(list);
            }
        });
    }
}
