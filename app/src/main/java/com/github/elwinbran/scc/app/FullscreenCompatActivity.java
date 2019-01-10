package com.github.elwinbran.scc.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.elwinbran.scc.com.githhub.elwinbran.scs.support.Executor;


/**
 * A specific extendable (non-optional) activity that tries to make the extending activity
 * to be fullscreen.
 *
 * Extenders are forced to call {@link #onCreate(Bundle)} after having themselves call
 * {@link #setContentView}.
 * The fullscreen settings: Rotatable, Sticky, Hidden Navigation.
 *
 * NOTE: Code is a refactored version of default fullscreen code that Android Studio generates.
 *
 * @author Elwin Slokker
 */
public abstract class FullscreenCompatActivity extends AppCompatActivity
{
    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private Executor systemUIHider;

    private View rootView;

    /**
     * Only call after setting the layout!
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final int binaryFullscreenSettings = View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        final int uiAnimationDelay = 300;
        final Handler hideHandler = new Handler();
        rootView.setSystemUiVisibility(binaryFullscreenSettings);
        final Runnable fullscreenSetter =
                new SystemUiVisibilitySetter(rootView, binaryFullscreenSettings);
        final Runnable actionbarShower = new Runnable()
        {
            @Override
            public void run()
            {
                // Delayed display of UI elements
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null)
                {
                    actionBar.show();
                }
            }
        };
        Executor baseHider = new DefaultHider(this, hideHandler, fullscreenSetter, uiAnimationDelay);
        final Executor hider = new RemoveCallbackWrapper(hideHandler, actionbarShower, baseHider);

        final Runnable runnableHider = new SimpleExecutorRunner(hider);
        systemUIHider = new DelayedHider(hideHandler, runnableHider, 100);
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        this.systemUIHider.execute();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        this.systemUIHider.execute();
    }

    /**
     * Sets the view that is highest in the hierarchy.
     * The fullscreen settings will be applied on this view.
     *
     * @param rootView The {@link View View Object} that is highest in the layout.
     */
    protected final void assign(@Nullable View rootView)
    {
        this.rootView = rootView;
    }
}
