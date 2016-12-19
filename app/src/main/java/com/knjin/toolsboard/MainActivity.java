package com.knjin.toolsboard;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.knjin.toolsboard.Material_Design_Login.LoginActivity;
import com.aheadle.toolsboard.R;
import com.knjin.toolsboard.SearchTransition.mainscreen.SearchTransitionActivity;
import com.knjin.toolsboard.TedBottomPicker.PickerActivity;
import com.knjin.toolsboard.WeChatImage.WeChatImageActivity;
import com.knjin.toolsboard.locationmanager.LocationActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button scView;
    Button tedBom;
    Button strBtn;
    Button locBtn;
    Button enlargeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();//初始化数据
        initView();//初始化界面
    }

    private void initView() {
        scView.setOnClickListener(MainActivity.this);
        tedBom.setOnClickListener(MainActivity.this);
        strBtn.setOnClickListener(MainActivity.this);
        locBtn.setOnClickListener(this);
        enlargeBtn.setOnClickListener(this);
    }

    private void initData() {
        scView = (Button) findViewById(R.id.scView);
        tedBom = (Button) findViewById(R.id.tedBom);
        strBtn = (Button) findViewById(R.id.stran);
        locBtn = (Button) findViewById(R.id.location);
        enlargeBtn = (Button) findViewById(R.id.wechat_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scView:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
            case R.id.tedBom:
                startActivity(new Intent(MainActivity.this, PickerActivity.class));
                break;
            case R.id.stran:
                startActivity(new Intent(MainActivity.this, SearchTransitionActivity.class));
                break;
            case R.id.location:
                startActivity(new Intent(MainActivity.this, LocationActivity.class));
                break;
            case R.id.wechat_image:
                startActivity(new Intent(MainActivity.this, WeChatImageActivity.class));
                break;
            default:break;
        }
    }
}
