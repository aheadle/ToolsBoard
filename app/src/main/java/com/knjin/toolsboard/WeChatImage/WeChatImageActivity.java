package com.knjin.toolsboard.WeChatImage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aheadle.toolsboard.R;
import com.bumptech.glide.Glide;

/**
 * 图像主界面，小图像
 */
public class WeChatImageActivity extends AppCompatActivity {
    ImageInfoObj mImageInfoObj;
    ImageWidgetInfoObj mImageWidgetInfoObj;
    ImageView iv_main;
    private String url = "http://ww1.sinaimg.cn/large/006y8lVagw1faj5jbq8khj318q18q0vb.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_image);
        iv_main = (ImageView) findViewById(R.id.iv_wechat_main);
        initData();
    }

    private void initData() {
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(iv_main);

        mImageInfoObj = new ImageInfoObj();
        mImageInfoObj.setImageUrl(url);
        mImageInfoObj.setImageWidth(1280);
        mImageInfoObj.setImageHeight(760);

        mImageWidgetInfoObj = new ImageWidgetInfoObj();
        mImageWidgetInfoObj.setX(iv_main.getLeft());
        mImageWidgetInfoObj.setY(iv_main.getTop());
        mImageWidgetInfoObj.setWidth(iv_main.getLayoutParams().width);
        mImageWidgetInfoObj.setHeight(iv_main.getLayoutParams().height);

        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeChatImageActivity.this, EnlargeActivity.class);
                intent.putExtra("imageInfoObj", mImageInfoObj);
                intent.putExtra("imageWidgetInfoObj", mImageWidgetInfoObj);
                startActivity(intent);
            }
        });

    }
}
