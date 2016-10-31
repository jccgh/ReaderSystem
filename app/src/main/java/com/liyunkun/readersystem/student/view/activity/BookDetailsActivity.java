package com.liyunkun.readersystem.student.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookBean;

public class BookDetailsActivity extends BaseActivity {

    private BookBean bookBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        initData2BookBean();
    }

    private void initData2BookBean() {
        Intent intent = getIntent();
        bookBean = (BookBean) intent.getSerializableExtra("bookBean");
    }
}
