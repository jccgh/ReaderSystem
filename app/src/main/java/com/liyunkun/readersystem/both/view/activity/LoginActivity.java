package com.liyunkun.readersystem.both.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.administrator.view.activity.AdministratorHomeActivity;
import com.liyunkun.readersystem.both.module.bean.UserBean;
import com.liyunkun.readersystem.both.utils.UserInputUtils;
import com.liyunkun.readersystem.both.view.intf.ILoginView;
import com.liyunkun.readersystem.student.view.activity.StudentHomeActivity;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends BaseActivity implements ILoginView {

    private Button mLogin;
    private TextView mResent;
    private EditText mUserName;
    private EditText mPassword;
    private ImageView mIvPassword;
    private ImageView mIvUserName;
    private Spinner mType;
    private int position;
    private CheckBox mCb;
    private SharedPreferences sp;
    private BmobQuery<UserBean> query = new BmobQuery<>();
    private String[] str = new String[]{MyConstants.ADMINISTRATOR, MyConstants.STUDENT};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        if (sp.getBoolean("isRemember", false)) {
            String type = sp.getString("type", "");
            String userName = sp.getString("userName", "");
            if (type.equals(MyConstants.ADMINISTRATOR)) {
                Intent intent = new Intent(LoginActivity.this, AdministratorHomeActivity.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
                finish();
            } else if (MyConstants.STUDENT.equals(type)) {
                Intent intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
                finish();
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mType.setAdapter(adapter);
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoginActivity.this.position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putBoolean("isRemember", true)
                        .putString("userName",getUserName())
                        .putString("type",getType()).commit();
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (UserInputUtils.isRight(getUserName(), getPassword())) {
                    case 0:
                        Toast.makeText(LoginActivity.this, "输入格式不正确", Toast.LENGTH_SHORT).show();
                        mUserName.setText("");
                        mPassword.setText("");
                        break;
                    case 1:
                        Toast.makeText(LoginActivity.this, "用户名格式错误", Toast.LENGTH_SHORT).show();
                        mUserName.setText("");
                        mPassword.setText("");
                        break;
                    case 2:
                        Toast.makeText(LoginActivity.this, "密码格式错误", Toast.LENGTH_SHORT).show();
                        mPassword.setText("");
                        break;
                    case 3:
                        query.addWhereEqualTo("userName", getUserName());
                        query.addWhereEqualTo("type", getType());
                        query.findObjects(new FindListener<UserBean>() {
                            @Override
                            public void done(List<UserBean> list, BmobException e) {
                                if (e == null) {
                                    if (list.size() > 0 && list != null) {
                                        if (getPassword().equals(list.get(0).getPassword())) {
                                            if (getType().equals("管理员")) {
                                                Intent intent = new Intent(LoginActivity.this, AdministratorHomeActivity.class);
                                                intent.putExtra("userName",getUserName());
                                                startActivity(intent);
                                                finish();
                                            } else if ("学生".equals(getType())) {
                                                Intent intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
                                                intent.putExtra("userName",getUserName());
                                                startActivity(intent);
                                                finish();
                                            }
                                        } else {
                                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                                            mPassword.setText("");
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "用户名或类型错误", Toast.LENGTH_SHORT).show();
                                        mUserName.setText("");
                                        mPassword.setText("");
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        break;
                }

            }
        });
        mResent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResentActivity.class));
            }
        });
        mIvPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassword.setText("");
            }
        });
        mIvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName.setText("");
            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.toString().length();
                if (length > 0) {
                    showIvPassword();
                } else {
                    hideIvPassword();
                }
            }
        });
        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.toString().length();
                if (length > 0) {
                    showIvUserName();
                } else {
                    hideIvUserName();
                }
            }
        });
    }

    private void findView() {
        mCb = ((CheckBox) findViewById(R.id.cb));
        sp = getSharedPreferences(MyConstants.REMEMBER, MODE_PRIVATE);
        mLogin = ((Button) findViewById(R.id.login));
        mResent = ((TextView) findViewById(R.id.resent));
        mUserName = (EditText) findViewById(R.id.userName);
        mPassword = ((EditText) findViewById(R.id.password));
        mIvPassword = ((ImageView) findViewById(R.id.iv_password));
        mIvUserName = ((ImageView) findViewById(R.id.iv_username));
        mType = ((Spinner) findViewById(R.id.type));
    }


    @Override
    public void showIvPassword() {
        mIvPassword.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIvPassword() {
        mIvPassword.setVisibility(View.GONE);
    }

    @Override
    public void showIvUserName() {
        mIvUserName.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideIvUserName() {
        mIvUserName.setVisibility(View.GONE);
    }

    @Override
    public String getType() {
        return str[position];
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPassword.getText().toString();
    }
}
