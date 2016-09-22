package com.knjin.toolsboard.SearchTransition.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;

import com.aheadle.toolsboard.R;
import com.knjin.toolsboard.SearchTransition.boilerplate.BoilerplateActivity;
import com.knjin.toolsboard.SearchTransition.searchscreen.SearchActivity;
import com.knjin.toolsboard.SearchTransition.transition.FadeInTransition;
import com.knjin.toolsboard.SearchTransition.transition.FadeOutTransition;
import com.knjin.toolsboard.SearchTransition.transition.SimpleTransitionListener;

public class SearchTransitionActivity extends BoilerplateActivity {
    private SimpleToolbar toolbar;
    private int toolbarMargin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_transition);

        toolbar = (SimpleToolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbarMargin = getResources().getDimensionPixelSize(R.dimen.toolbarMargin);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboard();
                transitionToSearch();
            }
        });
    }

    private void transitionToSearch() {
        Transition transition = FadeOutTransition.withAction(navigateToSearchWhenDone());
        TransitionManager.beginDelayedTransition(toolbar,transition);
        FrameLayout.LayoutParams frameLP = (FrameLayout.LayoutParams) toolbar.getLayoutParams();
        frameLP.setMargins(0,0,0,0);
        toolbar.setLayoutParams(frameLP);
        toolbar.hideContent();
    }

    private Transition.TransitionListener navigateToSearchWhenDone(){
        return new SimpleTransitionListener(){
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                Intent intent = new Intent(SearchTransitionActivity.this, SearchActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        fadeToolbarIn();
    }
    private void fadeToolbarIn(){
        TransitionManager.beginDelayedTransition(toolbar, FadeInTransition.createTransition());
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.setMargins(toolbarMargin,toolbarMargin,toolbarMargin,toolbarMargin);
        toolbar.showContent();
        toolbar.setLayoutParams(layoutParams);
    }
}
