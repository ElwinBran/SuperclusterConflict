package com.github.elwinbran.android.scc.app;

import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.github.elwinbran.android.scc.support.Executor;

public class DefaultHider implements Executor
{
    private final AppCompatActivity app;

    private final Handler handler;

    private final Runnable hideAction;

    private final int delay;

    public DefaultHider(AppCompatActivity app, Handler handler, Runnable hideAction, int delay)
    {
        this.app = app;
        this.handler = handler;
        this.hideAction = hideAction;
        this.delay = delay;
    }

    @Override
    public void execute()
    {
        // Hide UI first
        ActionBar actionBar = app.getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }
        //mControlsView.setVisibility(View.GONE);
        //mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        handler.postDelayed(hideAction, delay);
    }
}
