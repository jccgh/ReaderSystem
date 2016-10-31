package com.liyunkun.readersystem.student.module.intf;

import com.liyunkun.readersystem.administrator.module.intf.IBookCallBack;

/**
 * Created by liyunkun on 2016/10/31 0031.
 */
public interface IBookDetailsData {
    void getData2LvType(String bookName,int classId, IBookCallBack callBack);
    void getData2LvAuthor(String bookName,String author, IBookCallBack callBack);
}
