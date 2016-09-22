package com.knjin.toolsboard.SearchTransition.transition;

import android.transition.AutoTransition;
import android.transition.Transition;

/**
 * Created by Jing on 16/9/21.
 */

public class FadeInTransition extends AutoTransition{

    private static final int FADE_IN_DURATION = 200;
    private FadeInTransition(){

    }
    public static Transition createTransition(){
        AutoTransition transition = new AutoTransition();
        transition.setDuration(FADE_IN_DURATION);
        return transition;
    }

}
