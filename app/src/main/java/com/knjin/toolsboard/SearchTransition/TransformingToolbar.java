package com.knjin.toolsboard.SearchTransition;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Jing on 16/9/21.
 */

public class TransformingToolbar extends android.support.v7.widget.Toolbar {
    public TransformingToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void hideContent(){
        for (int i = 0;i < getChildCount();i++){
            getChildAt(i).setVisibility(GONE);
        }
    }

    public void showContent(){
        for (int i = 0;i < getChildCount();i++){
            getChildAt(i).setVisibility(VISIBLE);
        }
    }
}
