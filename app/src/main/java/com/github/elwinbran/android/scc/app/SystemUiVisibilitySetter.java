package com.github.elwinbran.android.scc.app;

import android.view.View;

/**
 * Applies the given value to the view when ran.
 *
 * @author Elwin Slokker
 */
public class SystemUiVisibilitySetter implements Runnable
{
    private final View targetView;

    private final int argument;

    public SystemUiVisibilitySetter(View targetView, int argument)
    {
        this.targetView = targetView;
        this.argument = argument;
    }

    @Override
    public void run()
    {
        targetView.setSystemUiVisibility(argument);
    }
}
