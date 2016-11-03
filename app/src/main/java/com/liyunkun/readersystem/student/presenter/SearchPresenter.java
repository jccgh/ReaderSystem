package com.liyunkun.readersystem.student.presenter;

import com.liyunkun.readersystem.BasePresenter;
import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.impl.ISearchDataImpl;
import com.liyunkun.readersystem.student.module.intf.ISearchData;
import com.liyunkun.readersystem.student.view.intf.ISearchView;

import java.util.List;

/**
 * Created by liyunkun on 2016/11/2 0002.
 */
public class SearchPresenter implements BasePresenter{
    private ISearchData data;
    private ISearchView view;

    public SearchPresenter(ISearchView view) {
        this.view = view;
        data=new ISearchDataImpl();
    }


    @Override
    public void start() {

    }

    public void start(String inputText) {
        data.getData2Result(inputText, new IBookCallBack() {
            @Override
            public void onFailed(String errorMsg) {

            }

            @Override
            public void onSuccessful(List<BookBean> list) {
                view.searchData(list);
            }
        });
    }
}
