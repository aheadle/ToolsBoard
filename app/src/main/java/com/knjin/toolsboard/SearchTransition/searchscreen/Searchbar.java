package com.knjin.toolsboard.SearchTransition.searchscreen;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.aheadle.toolsboard.R;
import com.knjin.toolsboard.SearchTransition.TransformingToolbar;

/**
 * Created by Jing on 16/9/21.
 */

public class Searchbar extends TransformingToolbar {
    private EditText editText;
    public Searchbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(context.getResources().getColor(android.R.color.white));
        setNavigationIcon(R.drawable.ic_keyboard_backspace);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(),R.layout.merge_search,this);
        editText = (EditText) findViewById(R.id.toolbar_search_edittext);
    }

    @Override
    public void showContent() {
        super.showContent();
        editText.requestFocus();
    }

    public void clearText(){
        editText.setText(null);
    }
}
