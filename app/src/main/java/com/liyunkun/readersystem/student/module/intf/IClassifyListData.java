package com.liyunkun.readersystem.student.module.intf;

import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public interface IClassifyListData {
    void getData(int classId, IBookCallBack callBack);
}
