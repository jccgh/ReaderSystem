package com.liyunkun.readersystem.student.view.activity;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.student.module.bean.StudentPwLvBean;
import com.liyunkun.readersystem.student.view.adapter.StudentHomeVpAdapter;
import com.liyunkun.readersystem.student.view.adapter.StudentPwLvAdapter;
import com.liyunkun.readersystem.student.view.fragment.BookShopFragment;
import com.liyunkun.readersystem.student.view.fragment.FindFragment;
import com.liyunkun.readersystem.student.view.fragment.MyBookFragment;

import java.util.ArrayList;
import java.util.List;

public class StudentHomeActivity extends BaseActivity implements View.OnClickListener {


    private RadioGroup mRg;
    private ViewPager mVp;
    private List<Fragment> mVpList;
    private TextView mTitle;
    private ImageView mIvMore;
    private SimpleDraweeView mIvMine;
    private ImageView mSearch;
    private DrawerLayout mDrawerLayout;
    private static String userName;
    private RadioButton mMyBook;
    private RadioButton mBookShop;
    private RadioButton mFind;
    private PopupWindow mPw;
    private SimpleDraweeView mDrawerUserIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_home);
        findView();
        initData2Fragment();
        setAdapter2Vp();
        initImage2UserIcon();
        mIvMine.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) mRg.getChildAt(position)).setChecked(true);
                switch (position) {
                    case 0:
                        mMyBook.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mBookShop.setTextColor(getResources().getColor(R.color.white));
                        mFind.setTextColor(getResources().getColor(R.color.white));
                        mIvMore.setVisibility(View.VISIBLE);
                        mTitle.setText("好优阅读");
                        break;
                    case 1:
                        mMyBook.setTextColor(getResources().getColor(R.color.white));
                        mBookShop.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mFind.setTextColor(getResources().getColor(R.color.white));
                        mIvMore.setVisibility(View.GONE);
                        mTitle.setText("书城");
                        break;
                    case 2:
                        mMyBook.setTextColor(getResources().getColor(R.color.white));
                        mBookShop.setTextColor(getResources().getColor(R.color.white));
                        mFind.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mIvMore.setVisibility(View.GONE);
                        mTitle.setText("发现");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.my_book:
                        mVp.setCurrentItem(0);
                        mMyBook.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mBookShop.setTextColor(getResources().getColor(R.color.white));
                        mFind.setTextColor(getResources().getColor(R.color.white));
                        mIvMore.setVisibility(View.VISIBLE);
                        mTitle.setText(getResources().getString(R.string.app_name2));
                        break;
                    case R.id.book_shop:
                        mVp.setCurrentItem(1);
                        mMyBook.setTextColor(getResources().getColor(R.color.white));
                        mBookShop.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mFind.setTextColor(getResources().getColor(R.color.white));
                        mIvMore.setVisibility(View.GONE);
                        mTitle.setText(getResources().getString(R.string.book_shop));
                        break;
                    case R.id.find:
                        mVp.setCurrentItem(2);
                        mMyBook.setTextColor(getResources().getColor(R.color.white));
                        mBookShop.setTextColor(getResources().getColor(R.color.white));
                        mFind.setTextColor(getResources().getColor(R.color.colorPrimary2));
                        mIvMore.setVisibility(View.GONE);
                        mTitle.setText(getResources().getString(R.string.find));
                        break;
                }
            }
        });
    }

    private void initImage2UserIcon() {
        Uri uri=Uri.parse("res:///"+R.drawable.default_user_icon);
        mIvMine.setImageURI(uri);
        mDrawerUserIcon.setImageURI(uri);
    }

    private void setAdapter2Vp() {
        StudentHomeVpAdapter vpAdapter =  new StudentHomeVpAdapter(getSupportFragmentManager(), mVpList);
        mVp.setAdapter(vpAdapter);
    }

    private void initData2Fragment() {
        mVpList = new ArrayList<>();
        mVpList.add(new MyBookFragment());
        mVpList.add(new BookShopFragment());
        mVpList.add(new FindFragment());
    }

    private void findView() {
        mRg = ((RadioGroup) findViewById(R.id.rg));
        mVp = ((ViewPager) findViewById(R.id.vp));
        mDrawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));
        mIvMine = ((SimpleDraweeView) findViewById(R.id.iv_mine));
        mTitle = ((TextView) findViewById(R.id.title));
        mMyBook = ((RadioButton) findViewById(R.id.my_book));
        mBookShop = ((RadioButton) findViewById(R.id.book_shop));
        mFind = ((RadioButton) findViewById(R.id.find));
        mIvMore = ((ImageView) findViewById(R.id.more));
        mSearch = ((ImageView) findViewById(R.id.search));
        mDrawerUserIcon = ((SimpleDraweeView) findViewById(R.id.iv_user_face));
        userName = getIntent().getStringExtra("userName");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                Toast.makeText(StudentHomeActivity.this, "search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more:
            {
                View view = LayoutInflater.from(StudentHomeActivity.this).inflate(R.layout.study_home_popup_window, null);
                ListView lv = (ListView) view.findViewById(R.id.lv);
                final List<StudentPwLvBean> lvBeen=new ArrayList<>();
                lvBeen.add(new StudentPwLvBean(R.drawable.shelf_edit_img,"编辑"));
                lvBeen.add(new StudentPwLvBean(R.drawable.shelf_list_icon,"列表模式"));
                lvBeen.add(new StudentPwLvBean(R.drawable.shelf_import_icon,"本地传书"));
                lvBeen.add(new StudentPwLvBean(R.drawable.local_shelf_books,"本地书籍"));
                lvBeen.add(new StudentPwLvBean(R.drawable.shelf_feedback_icon,"意见反馈"));
                StudentPwLvAdapter adapter=new StudentPwLvAdapter(lvBeen,this);
                lv.setAdapter(adapter);
                mPw = new PopupWindow(view,
                        (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,getResources().getDisplayMetrics())),
                        (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,240,getResources().getDisplayMetrics())));
                mPw.setOutsideTouchable(true);
                mPw.setBackgroundDrawable(new BitmapDrawable());
                mPw.showAsDropDown(mIvMore,0,20);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(StudentHomeActivity.this, lvBeen.get(position).getContent(), Toast.LENGTH_SHORT).show();
                        mPw.dismiss();
                    }
                });
            }
                break;
            case R.id.iv_mine:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }
}
