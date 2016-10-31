package com.liyunkun.readersystem.administrator.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.administrator.presenter.AdminPresenter;
import com.liyunkun.readersystem.administrator.view.intf.IAdminView;
import com.liyunkun.readersystem.both.module.bean.BookBean;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AdministratorHomeActivity extends BaseActivity implements IAdminView {

    private AdminPresenter presenter = new AdminPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_home);
    }

    public void saveBookClass(View view) {
        List<BookClassBean> bookClassBeen = MyConstants.list;
        for (BookClassBean bookClassBean : bookClassBeen) {
            bookClassBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(AdministratorHomeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void saveBook(View view) {
        List<BookClassBean> bookClassBeen = MyConstants.list;
        for (BookClassBean bookClassBean : bookClassBeen) {
            int classId = bookClassBean.getClassId();
            int totalBook = bookClassBean.getTotalBook();
            presenter.start(classId, totalBook);
        }
    }

    @Override
    public void saveData2Book(List<BookBean> list) {
        for (BookBean bookBean : list) {
            bookBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(AdministratorHomeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
