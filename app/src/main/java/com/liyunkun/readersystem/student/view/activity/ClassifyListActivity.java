package com.liyunkun.readersystem.student.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;

public class ClassifyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_list);
        Intent intent = getIntent();
        BookClassBean bookClassBean = (BookClassBean) intent.getSerializableExtra("bookClassBean");
        Log.d("liyunkundebugprint", "onCreate: "+bookClassBean.getType());
    }
}
