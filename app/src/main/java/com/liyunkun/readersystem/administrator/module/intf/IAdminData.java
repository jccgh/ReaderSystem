package com.liyunkun.readersystem.administrator.module.intf;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public interface IAdminData {
    void saveData2Book(int classId,int totalCount,IBookCallBack callBack);
}
