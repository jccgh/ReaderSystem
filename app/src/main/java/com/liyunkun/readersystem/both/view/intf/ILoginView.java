package com.liyunkun.readersystem.both.view.intf;

/**
 * Created by liyunkun on 2016/10/13 0013.
 */
public interface ILoginView {

    void showIvPassword();

    void hideIvPassword();

    void showIvUserName();

    void hideIvUserName();

    String getType();

    String getUserName();

    String getPassword();
}
