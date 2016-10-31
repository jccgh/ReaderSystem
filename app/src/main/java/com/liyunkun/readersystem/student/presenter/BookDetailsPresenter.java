package com.liyunkun.readersystem.student.presenter;

import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.impl.IBookDetailsImpl;
import com.liyunkun.readersystem.student.module.intf.IBookDetailsData;
import com.liyunkun.readersystem.student.view.intf.IBookDetailsView;

import java.util.List;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public class BookDetailsPresenter {
    private IBookDetailsData data;
    private IBookDetailsView view;

    public BookDetailsPresenter(IBookDetailsView view) {
        this.view = view;
        data=new IBookDetailsImpl();
    }
    public void start(String bookName,int classId){
        data.getData2LvType(bookName,classId, new IBookCallBack() {
            @Override
            public void onFailed(String errorMsg) {

            }

            @Override
            public void onSuccessful(List<BookBean> list) {
                view.setData2LvType(list);
            }
        });
    }

    public void start(String bookName,String author) {
        data.getData2LvAuthor(bookName,author, new IBookCallBack() {
            @Override
            public void onFailed(String errorMsg) {

            }

            @Override
            public void onSuccessful(List<BookBean> list) {
                view.setData2LvAuthor(list);
            }
        });
    }
}
