package com.liyunkun.readersystem.utils;

import android.content.Context;

import com.liyunkun.readersystem.both.module.bean.BookBean;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by liyunkun on 2016/11/5 0005.
 */
public class ShareUtil {
    public static void share(BookBean bookBean, Context context){
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(bookBean.getName());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(bookBean.getBookImg());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(bookBean.getDescription());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(bookBean.getBookImg());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(bookBean.getBookImg());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(bookBean.getAuthor());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(bookBean.getName());
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(bookBean.getBookImg());
        // 启动分享GUI
        oks.show(context);
    }
}
