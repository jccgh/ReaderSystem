package com.liyunkun.readersystem.student.presenter;

import com.liyunkun.readersystem.BasePresenter;
import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.impl.IRecommendDataImpl;
import com.liyunkun.readersystem.student.module.intf.IRecommendData;
import com.liyunkun.readersystem.student.view.intf.IRecommendView;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public class RecommendPresenter implements BasePresenter{
    private IRecommendData data;
    private IRecommendView view;

    public RecommendPresenter(IRecommendView view) {
        this.view = view;
        data=new IRecommendDataImpl();
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
