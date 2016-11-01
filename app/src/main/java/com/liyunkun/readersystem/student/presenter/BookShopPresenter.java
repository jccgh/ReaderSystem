package com.liyunkun.readersystem.student.presenter;

import com.liyunkun.readersystem.BasePresenter;
import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.student.module.bean.BookShopLvBean;
import com.liyunkun.readersystem.student.module.impl.IBookShopDataImpl;
import com.liyunkun.readersystem.student.module.intf.IBookShopData;
import com.liyunkun.readersystem.student.view.intf.IBookShopView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyunkun on 2016/11/1 0001.
 */
public class BookShopPresenter implements BasePresenter{
    private IBookShopData data;
    private IBookShopView view;

    public BookShopPresenter(IBookShopView view) {
        this.view = view;
        data=new IBookShopDataImpl();
    }

    @Override
    public void start() {
        data.getData(new IBookCallBack() {
            @Override
            public void onFailed(String errorMsg) {

            }
            @Override
            public void onSuccessful(List<BookBean> list) {
                List<BookShopLvBean> beanList=new ArrayList<BookShopLvBean>();
                List<BookBean> list1 = list.subList(0, 6);
                List<BookBean> list2 = list.subList(6, 12);
                List<BookBean> list3 = list.subList(12, 18);
                List<BookBean> list4 = list.subList(18, 24);
                List<BookBean> list5 = list.subList(24, 30);
                beanList.add(new BookShopLvBean("重榜精选",list1));
                beanList.add(new BookShopLvBean("主编推荐",list2));
                beanList.add(new BookShopLvBean("火热新书",list3));
                beanList.add(new BookShopLvBean("热门作品",list4));
                beanList.add(new BookShopLvBean("与你相关",list5));
                view.initData2Lv(beanList);
            }
        });
    }
}
