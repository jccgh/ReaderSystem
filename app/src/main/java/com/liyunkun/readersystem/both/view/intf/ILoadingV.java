package com.liyunkun.readersystem.both.view.intf;

import com.liyunkun.readersystem.both.view.adapter.LoadingVpAdapter;

/**
 * Created by liyunkun on 2016/10/13 0013.
 * 引导界面view中的接口
 */
public interface ILoadingV {
    //为viewpager设置适配器
    void initData2ViewPager(LoadingVpAdapter adapter);

    //添加底部小圆点
    void initView2Layout();

    //显示进入按钮
    void showBtOpen();

    //小时隐藏按钮
    void hideBtOpen();
}
