package com.github.elwinbran.scc.app;

import android.os.Handler;

import com.github.elwinbran.scc.com.githhub.elwinbran.scs.support.Executor;

public class DelayedHider implements Executor
{
    private final Handler handler;

    private final Runnable hideRunnable;

    private final int delay;

    public DelayedHider(Handler handler, Runnable hideRunnable, int delay)
    {
        this.handler = handler;
        this.hideRunnable = hideRunnable;
        this.delay = delay;
    }

    @Override
    public void execute()
    {
        handler.removeCallbacks(hideRunnable);
        handler.postDelayed(hideRunnable, delay);
    }
}
