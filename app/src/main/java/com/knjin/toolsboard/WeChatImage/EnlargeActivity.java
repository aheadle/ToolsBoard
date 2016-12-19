package com.knjin.toolsboard.WeChatImage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aheadle.toolsboard.R;
import com.bumptech.glide.Glide;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;

/**
 * 放大后的图像界面
 */
public class EnlargeActivity extends AppCompatActivity {
    private RelativeLayout MainView;
    private ImageView showImageView;
    private ImageInfoObj imageInfoObj;
    private ImageWidgetInfoObj imageWidgetInfoObj;

    // 屏幕宽度
    public float Width;
    //原图高
    private float y_img_h;
    // 屏幕高度
    public float Height;
    private float size, size_h, img_w, img_h;
    protected float to_x = 0;
    protected float to_y = 0;
    private float tx;
    private float ty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarge);
        Bundle bundle = getIntent().getExtras();
        bundle.getParcelable("imageInfoObj");
        bundle.getParcelable("imageWidgetInfoObj");
        findId();
        init();
        Listener();
    }

    private final Spring spring = SpringSystem
            .create()
            .createSpring()
            .addListener(new ExampleSpringListener());


    private void findId() {
        MainView = (RelativeLayout) findViewById(R.id.activity_enlarge);
    }

    private void init() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Width = dm.widthPixels;
        Height = dm.heightPixels;

        imageInfoObj = getIntent().getParcelableExtra("imageInfoObj");
        imageWidgetInfoObj = getIntent().getParcelableExtra("imageWidgetInfoObj");
        showImageView = new ImageView(this);
        showImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(EnlargeActivity.this)
                .load(imageInfoObj.getImageUrl())
                .into(showImageView);

        img_w = imageWidgetInfoObj.getWidth();
        img_h = imageWidgetInfoObj.getHeight() - 300;
        size = Width / img_w;
        y_img_h = imageInfoObj.getImageHeight() * Width / imageInfoObj.getImageWidth();
        size_h = y_img_h / img_h;

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(imageWidgetInfoObj.getWidth(),
                imageWidgetInfoObj.getHeight());
        p.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        p.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        showImageView.setLayoutParams(p);
        p.setMargins(imageWidgetInfoObj.getX(),
                imageWidgetInfoObj.getY(), (int) (Width - (imageWidgetInfoObj.getX() + imageWidgetInfoObj.getWidth())),
                (int) (Height - (imageWidgetInfoObj.getY() + imageWidgetInfoObj.getHeight())));
        MainView.addView(showImageView);

        new Handler().post(new Runnable() {
            public void run() {
                ShowImageView();
            }
        });
    }

    private void Listener() {
        showImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowImageView();
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShowImageView();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class ExampleSpringListener implements SpringListener {

        @Override
        public void onSpringUpdate(Spring spring) {
            double CurrentValue = spring.getCurrentValue();
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(CurrentValue, 0, 1, 1, size);
            float mapy = (float) SpringUtil.mapValueFromRangeToRange(CurrentValue, 0, 1, 1, size_h);
            showImageView.setScaleX(mappedValue);
            showImageView.setScaleY(mapy);
            if (CurrentValue == 1) {
//                showImageView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onSpringAtRest(Spring spring) {

        }

        @Override
        public void onSpringActivate(Spring spring) {

        }

        @Override
        public void onSpringEndStateChange(Spring spring) {

        }
    }

    private void MoveView() {

        ObjectAnimator.ofFloat(MainView, "alpha", 0.8f).setDuration(0).start();
        MainView.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showImageView, "translationX", tx).setDuration(200),
                ObjectAnimator.ofFloat(showImageView, "translationY", ty).setDuration(200),
                ObjectAnimator.ofFloat(MainView, "alpha", 1).setDuration(200)

        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                showImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                spring.setEndValue(1);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();

    }

    private void MoveBackView() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showImageView, "translationX", to_x).setDuration(200),
                ObjectAnimator.ofFloat(showImageView, "translationY", to_y).setDuration(200)
        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    private void ShowImageView() {
        if (spring.getEndValue() == 0) {
            //弹动摩擦力
            spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(300, 5));
            tx = 0;
            //动画结束后出现的位置
            ty = Height / 2 - (imageWidgetInfoObj.getY() + img_h + 700);
            MoveView();
            return;
        }
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(1, 5));
        spring.setEndValue(0);
        new Handler().post(new Runnable() {
            public void run() {
                MoveBackView();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            showImageView.setVisibility(View.VISIBLE);
            ShowImageView();

        }
        return true;
    }
}
