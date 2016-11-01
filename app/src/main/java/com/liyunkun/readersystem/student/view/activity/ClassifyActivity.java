package com.liyunkun.readersystem.student.view.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.module.bean.BookClassBean;
import com.liyunkun.readersystem.student.view.adapter.ClassifyRvAdapter;
import com.liyunkun.readersystem.utils.MyConstants;

public class ClassifyActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRv;
    private ImageView mGoBack;
    private TextView mBookShelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        initView();
        initData2Rv();
        mGoBack.setOnClickListener(this);
        mBookShelf.setOnClickListener(this);
    }

    private void initData2Rv() {
        GridLayoutManager layout = new GridLayoutManager(this, 2);
        mRv.setLayoutManager(layout);
        mRv.addItemDecoration(new MyItemDecoration(10));
        ClassifyRvAdapter adapter = new ClassifyRvAdapter(MyConstants.list, this);
        mRv.setAdapter(adapter);
        adapter.setOnItemListener(new ClassifyRvAdapter.OnItemListener() {
            @Override
            public void onClick(BookClassBean bookClassBean) {
                Intent intent = new Intent(ClassifyActivity.this, ClassifyListActivity.class);
                intent.putExtra("bookClassBean",bookClassBean);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mRv = ((RecyclerView) findViewById(R.id.rv));
        mGoBack = ((ImageView) findViewById(R.id.go_back));
        mBookShelf = ((TextView) findViewById(R.id.book_shelf));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.book_shelf:
                startActivity(new Intent(this, StudentHomeActivity.class));
                break;
        }
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public MyItemDecoration(int space) {
            super();
            this.space = space;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
            if (parent.getChildLayoutPosition(view) % 2 == 0) {
                outRect.left = space;
            } else {
                outRect.left = 0;
            }
        }
    }
}
