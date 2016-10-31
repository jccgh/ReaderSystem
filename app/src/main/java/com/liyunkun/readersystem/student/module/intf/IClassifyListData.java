package com.liyunkun.readersystem.student.module.intf;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public interface IClassifyListData {
    void getData(int classId, int totalCount, IBookCallBack callBack);
}
