package com.aheadle.toolsboard.Material_Design_Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aheadle.toolsboard.R;

public class LoginActivity extends AppCompatActivity {
    private CatchScrollLayout catchScrollLayout;
    private SignUpContainer signUpContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        catchScrollLayout = (CatchScrollLayout) findViewById(R.id.catch_sroll_layout);
        signUpContainer = (SignUpContainer) findViewById(R.id.sign_up_container);
        catchScrollLayout.setIScrollCallBack(new CatchScrollLayout.IScrollCallBack() {
            @Override
            public void onScrollProcess(int process, boolean isLeft) {
                if (!isLeft) {
                    process = 100 - process;
                }
                signUpContainer.setAnimProportion(process);
            }
        });

        signUpContainer.setIConfirmCallBack(new SignUpContainer.IConfirmCallBack() {
            @Override
            public void goNext() {

            }
        });
    }
}
