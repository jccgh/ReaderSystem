package com.liyunkun.readersystem.both.utils;

/**
 * Created by liyunkun on 2016/10/13 0013.
 * 用户信息验证的工具类
 */
public class UserInputUtils {
    public static int isRight(String userName,String password){
        if(userName != null && password != null && userName.length() > 0 && password.length() > 0){
            if(userName.matches("[1-9][0-9]{9}")){
                if(password.matches("[0-9]{6}")){
                    return 3;//输入正确
                }else{
                    return 2;//密码错误
                }
            }else{
                return 1;//用户名输入有误
            }
        }else{
            return 0;//输入有误
        }
    }
}
