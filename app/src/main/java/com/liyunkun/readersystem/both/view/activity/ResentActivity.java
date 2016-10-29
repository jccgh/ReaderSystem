package com.liyunkun.readersystem.both.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.administrator.view.activity.AdministratorHomeActivity;
import com.liyunkun.readersystem.both.module.bean.UserBean;
import com.liyunkun.readersystem.both.utils.UserInputUtils;
import com.liyunkun.readersystem.student.view.activity.StudentHomeActivity;
import com.liyunkun.readersystem.utils.MyConstants;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

//忘记密码
public class ResentActivity extends BaseActivity {

    private Spinner mType;
    private EditText mUserName;
    private ImageView mIvUserName;
    private EditText mPassword;
    private ImageView mIvPassword;
    private EditText mPassword2;
    private ImageView mIvPassword2;
    private Button mUpdate;
    private int position;
    private ArrayAdapter adapter;
    private BmobQuery<UserBean> query=new BmobQuery<>();
    private String[] str = new String[]{MyConstants.ADMINISTRATOR, MyConstants.STUDENT};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resent);
        findView();
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNull()){
                    switch (UserInputUtils.isRight(getUserName(),getPassword())){
                        case 1:
                            Toast.makeText(ResentActivity.this, "用户名格式不正确", Toast.LENGTH_SHORT).show();
                            mUserName.setText("");
                            mPassword.setText("");
                            mPassword2.setText("");
                            break;
                        case 2:
                            Toast.makeText(ResentActivity.this, "密码格式不正确", Toast.LENGTH_SHORT).show();
                            mPassword.setText("");
                            mPassword2.setText("");
                            break;
                        case 3:
                            if(isTrue()){
                                query.addWhereEqualTo("type",getType());
                                query.addWhereEqualTo("userName",getUserName());
                                query.findObjects(new FindListener<UserBean>() {
                                    @Override
                                    public void done(List<UserBean> list, BmobException e) {
                                        if(e == null){
                                            if(list.size() > 0 && list != null){
                                                UserBean userBean = list.get(0);
                                                userBean.setPassword(getPassword());
                                                userBean.update(new UpdateListener() {
                                                    @Override
                                                    public void done(BmobException e) {
                                                        if(e == null){
                                                            if(MyConstants.ADMINISTRATOR.equals(getType())){
                                                                Intent intent=new Intent(ResentActivity.this,AdministratorHomeActivity.class);
                                                                intent.putExtra("userName",getUserName());
                                                                startActivity(intent);
                                                                finish();
                                                            }else if(MyConstants.STUDENT.equals(getType())){
                                                                Intent intent=new Intent(ResentActivity.this,StudentHomeActivity.class);
                                                                intent.putExtra("userName",getUserName());
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                        }
                                                    }
                                                });
                                            }else{
                                                Toast.makeText(ResentActivity.this, "用户名或类型错误", Toast.LENGTH_SHORT).show();
                                                mUserName.setText("");
                                                mPassword.setText("");
                                                mPassword2.setText("");
                                            }
                                        }else{
                                            Toast.makeText(ResentActivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(ResentActivity.this, "密码核对不正确", Toast.LENGTH_SHORT).show();
                                mPassword.setText("");
                                mPassword2.setText("");
                            }
                            break;
                    }
                }else{
                    Toast.makeText(ResentActivity.this, "请输入数据", Toast.LENGTH_SHORT).show();
                    mUserName.setText("");
                    mPassword.setText("");
                    mPassword2.setText("");
                }
            }
        });
        mIvPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassword2.setText("");
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
        mType.setAdapter(adapter);
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ResentActivity.this.position=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0){
                    showIvPassword2();
                }else{
                    hideIvPassword2();
                }
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
                if(s != null && s.length() > 0){
                    showIvPassword();
                }else{
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
                if (s != null && s.length() > 0){
                    showIvUserName();
                }else{
                    hideIvUserName();
                }
            }
        });

    }

    private void findView() {
        adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mType = ((Spinner) findViewById(R.id.type));
        mUserName = ((EditText) findViewById(R.id.userName));
        mIvUserName = ((ImageView) findViewById(R.id.iv_username));
        mPassword = ((EditText) findViewById(R.id.password));
        mIvPassword = ((ImageView) findViewById(R.id.iv_password));
        mPassword2 = ((EditText) findViewById(R.id.password2));
        mIvPassword2 = ((ImageView) findViewById(R.id.iv_password2));
        mUpdate = ((Button) findViewById(R.id.update));
    }

    /**
     * 判断两个密码是否一致
     * @return
     */
    private boolean isTrue(){
        if(getPassword().equals(getPassword2())){
            return true;
        }
        return false;
    }

    /**
     * 判断用户是否输入空数据
     * @return
     */
    private boolean isNull(){
        if(getUserName().length() > 0 &&
                getUserName() != null &&
                getPassword().length() > 0 &&
                getPassword() != null &&
                getPassword2().length() > 0 &&
                getPassword2() != null){
            return true;
        }
        return false;
    }
    private String getUserName() {
        return mUserName.getText().toString();
    }

    private String getPassword() {
        return mPassword.getText().toString();
    }

    private String getType() {
        return str[position];
    }

    private String getPassword2() {
        return mPassword2.getText().toString();
    }

    private void showIvUserName() {
        mIvUserName.setVisibility(View.VISIBLE);
    }

    private void hideIvUserName() {
        mIvUserName.setVisibility(View.GONE);
    }

    private void showIvPassword() {
        mIvPassword.setVisibility(View.VISIBLE);
    }

    private void hideIvPassword() {
        mIvPassword.setVisibility(View.GONE);
    }

    private void showIvPassword2() {
        mIvPassword2.setVisibility(View.VISIBLE);
    }

    private void hideIvPassword2() {
        mIvPassword2.setVisibility(View.GONE);
    }

}
