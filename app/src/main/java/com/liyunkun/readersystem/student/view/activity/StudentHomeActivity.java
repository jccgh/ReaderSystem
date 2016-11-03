package com.liyunkun.readersystem.student.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.both.view.activity.LoginActivity;
import com.liyunkun.readersystem.student.module.bean.StudentPwLvBean;
import com.liyunkun.readersystem.student.view.adapter.StudentHomeVpAdapter;
import com.liyunkun.readersystem.student.view.adapter.StudentPwLvAdapter;
import com.liyunkun.readersystem.student.view.fragment.BookShopFragment;
import com.liyunkun.readersystem.student.view.fragment.FindFragment;
import com.liyunkun.readersystem.student.view.fragment.MyBookFragment;
import com.liyunkun.readersystem.utils.MyConstants;

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
    public static String userName;
    private RadioButton mMyBook;
    private RadioButton mBookShop;
    private RadioButton mFind;
    private PopupWindow mPw;
    private SimpleDraweeView mDrawerUserIcon;
    private StudentHomeVpAdapter vpAdapter;
    private List<StudentPwLvBean> lvBeen;
    private StudentPwLvAdapter adapter;
    private SharedPreferences sp;
    private TextView mTvUserName;
    private Button mQuit;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_home);
        findView();
        initData2Fragment();
        setAdapter2Vp();
        mVp.setCurrentItem(0);
        mRg.getChildAt(0).setEnabled(true);
        initImage2UserIcon();
        mIvMine.setOnClickListener(this);
        mIvMore.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mQuit.setOnClickListener(this);
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initImage2UserIcon() {
        Uri uri = Uri.parse("res:///" + R.drawable.default_user_icon);
        mIvMine.setImageURI(uri);
        mDrawerUserIcon.setImageURI(uri);
    }

    private void setAdapter2Vp() {
        vpAdapter = new StudentHomeVpAdapter(getSupportFragmentManager(), mVpList);
        mVp.setAdapter(vpAdapter);
    }

    private void initData2Fragment() {
        mVpList = new ArrayList<>();
        mVpList.add(new MyBookFragment());
        mVpList.add(new BookShopFragment());
        mVpList.add(new FindFragment());
    }

    private void findView() {
        preferences = getSharedPreferences(MyConstants.REMEMBER, MODE_PRIVATE);
        mQuit = ((Button) findViewById(R.id.quit));
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
        sp = getSharedPreferences(MyConstants.MODE_NUMBER, MODE_PRIVATE);
        MyConstants.mode = sp.getInt(MyConstants.MODE, MyConstants.mode);
        userName = getIntent().getStringExtra(MyConstants.USER_NAME);
        MyConstants.userName=userName;
        mTvUserName = ((TextView) findViewById(R.id.tv_user_name));

        mTvUserName.setText(userName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.more: {
                createPw();
            }
            break;
            case R.id.iv_mine:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.quit: {
                quit();
            }
            break;
        }
    }

    private void createPw() {
        View view = LayoutInflater.from(StudentHomeActivity.this).inflate(R.layout.study_home_popup_window, null);
        ListView lv = (ListView) view.findViewById(R.id.lv);
        lvBeen = new ArrayList<>();
        lvBeen.add(new StudentPwLvBean(R.drawable.shelf_edit_img, "编辑"));
        if (MyConstants.LIST_MODE == MyConstants.mode) {
            lvBeen.add(new StudentPwLvBean(R.drawable.shelf_map_icon, "书架模式"));
        } else if (MyConstants.BOOK_MODE == MyConstants.mode) {
            lvBeen.add(new StudentPwLvBean(R.drawable.shelf_list_icon, "列表模式"));
        }
        lvBeen.add(new StudentPwLvBean(R.drawable.shelf_import_icon, "本地传书"));
        lvBeen.add(new StudentPwLvBean(R.drawable.local_shelf_books, "本地书籍"));
        lvBeen.add(new StudentPwLvBean(R.drawable.shelf_feedback_icon, "意见反馈"));
        adapter = new StudentPwLvAdapter(lvBeen, this);
        lv.setAdapter(adapter);
        mPw = new PopupWindow(view,
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics())),
                (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics())));
        mPw.setOutsideTouchable(true);
        mPw.setBackgroundDrawable(new BitmapDrawable());
        mPw.showAsDropDown(mIvMore, 0, 20);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://编辑

                        break;
                    case 1://列表模式
                        if (MyConstants.LIST_MODE == MyConstants.mode) {
                            MyConstants.mode = MyConstants.BOOK_MODE;
                            sp.edit().putInt(MyConstants.MODE, MyConstants.mode).commit();
                            Intent intent = new Intent(StudentHomeActivity.this, StudentHomeActivity.class);
                            intent.putExtra(MyConstants.USER_NAME, userName);
                            startActivity(intent);
                        } else if (MyConstants.BOOK_MODE == MyConstants.mode) {
                            MyConstants.mode = MyConstants.LIST_MODE;
                            sp.edit().putInt(MyConstants.MODE, MyConstants.mode).commit();
                            Intent intent = new Intent(StudentHomeActivity.this, StudentHomeActivity.class);
                            intent.putExtra(MyConstants.USER_NAME, userName);
                            startActivity(intent);
                        }
                        break;
                    case 2://本地传书
                        break;
                    case 3://本地书籍
                        break;
                    case 4://意见反馈
                        break;

                }
                mPw.dismiss();
            }
        });
    }

    private void quit() {
        preferences.edit().putBoolean("isRemember", false).commit();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
