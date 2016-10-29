package com.liyunkun.readersystem.both.presenter;

import android.content.Context;

import com.liyunkun.readersystem.BasePresenter;
import com.liyunkun.readersystem.both.module.impl.ILoadingImpl;
import com.liyunkun.readersystem.both.module.inf.ILoading;
import com.liyunkun.readersystem.both.view.adapter.LoadingVpAdapter;
import com.liyunkun.readersystem.both.view.intf.ILoadingV;

/**
 * Created by liyunkun on 2016/10/13 0013.
 * 引导界面的中间者，用于view和module之间的交互
 */
public class LoadingPresenter implements BasePresenter{
    private ILoading iLoadingModule;//module中的接口
    private ILoadingV iLoadingView;//view中的接口
    private Context mContext;//上下文

    public LoadingPresenter(ILoadingV iLoadingView) {
        this.iLoadingModule = new ILoadingImpl();//定义其实现类
        this.iLoadingView = iLoadingView;
        mContext = (Context) iLoadingView;
    }

    //开启方法
    public void load() {
        int[] data = iLoadingModule.getData();//通过接口回调获取数据源
        //配置adapter
        LoadingVpAdapter adapter = new LoadingVpAdapter(data, mContext);
        iLoadingView.initData2ViewPager(adapter);//通过view中的接口来为ViewPager设置适配器
        iLoadingView.initView2Layout();//设置圆点
    }


    @Override
    public void start() {

    }
}
