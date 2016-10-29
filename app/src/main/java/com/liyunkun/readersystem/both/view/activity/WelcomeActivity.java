package com.liyunkun.readersystem.both.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.liyunkun.readersystem.BaseActivity;
import com.liyunkun.readersystem.R;
import com.liyunkun.readersystem.utils.MyConstants;

//欢迎界面
public class WelcomeActivity extends BaseActivity {

    private Button mBtJump;//跳过按钮
    private SharedPreferences sp;
    private boolean isLast;//是否为第一次登录
    private Handler mHandler;//handler
    private int totalTime = 5;//总的分钟数
    private int countTime = 0;//当前分钟数
    private boolean isRunning = true;//是否正在运行


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //查找控件
        mBtJump = (Button) findViewById(R.id.bt_jump);
        //初始化sp
        sp = getSharedPreferences(MyConstants.FIRSTDOWN, MODE_PRIVATE);
        isLast = sp.getBoolean("isLast", true);
        //初始化handler，用于时间的控制
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        //判断应用是否在运行，防止出现用户退出应用后子线程还在运行，或者用户点击跳过之后该界面的子线程还在运行
                        if (isRunning) {
                            //从新设置按钮中的文本
                            mBtJump.setText("跳过  " + totalTime--);
                            //如果时间到了总分钟数，就不再发送信息
                            if (countTime == totalTime) {
                                //验证用户是否第一次登录
                                if (isLast) {
                                    startActivity(new Intent(WelcomeActivity.this, LoadingActivity.class));
                                    finish();
                                } else {
                                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                                    finish();
                                }
                            } else {
                                //延迟发送一条消息
                                mHandler.sendEmptyMessageDelayed(0, 1000);
                            }
                        }
                        break;
                }
            }
        };
        //跳过的监听
        mBtJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLast) {
                    startActivity(new Intent(WelcomeActivity.this, LoadingActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
        //开启一个子线程，用于让该界面停止几秒
        new Thread(new Runnable() {
            @Override
            public void run() {
                //验证应用是否在运行，防止出现用户退出应用后子线程还在运行，或者用户点击跳过之后该界面的子线程还在运行
                //如果已退出该界面，就不再发送消息
                if (isRunning) {
                    mHandler.sendEmptyMessage(0);
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当销毁时，将正在运行设为false
        isRunning = false;
    }
}
