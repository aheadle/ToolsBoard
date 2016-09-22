package com.knjin.toolsboard.SearchTransition.boilerplate;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Jing on 16/9/21.
 */

public class BoilerplateActivity extends AppCompatActivity {
    /**
     * 隐藏键盘
     */
    protected void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
    }

    /**
     * 显示键盘
     */
    protected void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
    }

    protected void shareDemo(){
        ShareDemo shareDemo = new ShareDemo(this);
        shareDemo.shareDemo();
    }
}
