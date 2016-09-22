package com.knjin.toolsboard.SearchTransition.transition;

import android.transition.AutoTransition;
import android.transition.Transition;

/**
 * Created by Jing on 16/9/21.
 */

public class FadeOutTransition extends AutoTransition {

    private FadeOutTransition() {
        // force callers to call the factory method to instantiate this class
    }

    private static final int FADE_OUT_DURATION = 250;

    /**
     * Creates a AutoTransition that calls the {@linkplain android.transition.Transition.TransitionListener#onTransitionEnd(Transition)}
     * of the passing Listener when complete
     */
    public static Transition withAction(TransitionListener finishingAction) {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(FADE_OUT_DURATION);
        transition.addListener(finishingAction);
        return transition;
    }

}
