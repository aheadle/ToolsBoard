package com.knjin.toolsboard.SearchTransition.mainscreen;

import android.content.Context;
import android.util.AttributeSet;

import com.aheadle.toolsboard.R;
import com.knjin.toolsboard.SearchTransition.TransformingToolbar;

/**
 * Created by Jing on 16/9/21.
 */

public class SimpleToolbar extends TransformingToolbar {
    public SimpleToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(context.getResources().getColor(android.R.color.white));
        setNavigationIcon(R.drawable.ic_search);
    }
}
