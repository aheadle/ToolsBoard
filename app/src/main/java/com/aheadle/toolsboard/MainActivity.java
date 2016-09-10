package com.aheadle.toolsboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aheadle.toolsboard.Material_Design_Login.LoginActivity;
import com.aheadle.toolsboard.TedBottomPicker.PickerActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button scView;
    Button tedBom;
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
    }

    private void initData() {
        scView = (Button) findViewById(R.id.scView);
        tedBom = (Button) findViewById(R.id.tedBom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scView:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            break;
            case R.id.tedBom:
                startActivity(new Intent(MainActivity.this, PickerActivity.class));
            default:break;
        }
    }
}
