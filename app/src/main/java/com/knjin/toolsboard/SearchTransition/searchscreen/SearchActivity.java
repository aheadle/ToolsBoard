package com.knjin.toolsboard.SearchTransition.searchscreen;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.aheadle.toolsboard.R;
import com.knjin.toolsboard.SearchTransition.boilerplate.BoilerplateActivity;
import com.knjin.toolsboard.SearchTransition.transition.FadeInTransition;
import com.knjin.toolsboard.SearchTransition.transition.FadeOutTransition;
import com.knjin.toolsboard.SearchTransition.transition.SimpleTransitionListener;

public class SearchActivity extends BoilerplateActivity {
    private Searchbar searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchbar = (Searchbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(searchbar);

        if (isFirstTimeRunning(savedInstanceState)) {
            searchbar.hideContent();

            ViewTreeObserver viewTreeObserver = searchbar.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    searchbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    showSearch();
                }

                private void showSearch(){
                    TransitionManager.beginDelayedTransition(searchbar, FadeInTransition.createTransition());
                    searchbar.showContent();
                }
            });
        }
    }

    private boolean isFirstTimeRunning(Bundle savedInstanceState){
        return savedInstanceState == null;
    }

    @Override
    public void finish() {
        super.finish();
        hideKeyboard();

        exitTransitionWithAction(new Runnable() {
            @Override
            public void run() {
                SearchActivity.super.finish();
                overridePendingTransition(0,0);
            }
        });
    }

    private void exitTransitionWithAction(final Runnable endingAction){
        Transition transition = FadeOutTransition.withAction(new SimpleTransitionListener(){
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                endingAction.run();
            }
        });
        TransitionManager.beginDelayedTransition(searchbar,transition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }else if (item.getItemId() == R.id.action_clear){
            searchbar.clearText();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
