package com.knjin.toolsboard.TedBottomPicker.view;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.aheadle.toolsboard.R;

/**
 * Created by Jing on 16/9/10.
 */
public class TedSquareFrameLayout extends FrameLayout{

    private static boolean mMatchHeightToWidth;
    private static boolean mMatchWidthToHeight;

    public TedSquareFrameLayout(Context context) {
        super(context);
    }

    public TedSquareFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.TedSquareView,0,0);

        try {
            mMatchHeightToWidth = a.getBoolean(R.styleable.TedSquareView_matchHeightToWidth,false);
            mMatchWidthToHeight = a.getBoolean(R.styleable.TedSquareView_matchWidthToHeight,false);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mMatchHeightToWidth){
            setMeasuredDimension(widthMeasureSpec,widthMeasureSpec);
        }else if (mMatchWidthToHeight){
            setMeasuredDimension(heightMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        if (mMatchHeightToWidth) {
            super.onSizeChanged(w, w, oldw, oldh);
        }else if (mMatchWidthToHeight){
            super.onSizeChanged(h, h, oldw, oldh);
        }
    }
}
