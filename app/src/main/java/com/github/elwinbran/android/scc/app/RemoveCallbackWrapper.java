package com.github.elwinbran.android.scc.app;

import android.os.Handler;

import com.github.elwinbran.android.scc.support.Executor;

/**
 * Removes somthing from the handler first and then passes to next executor.
 *
 * @author Elwin Slokker
 */
public class RemoveCallbackWrapper implements Executor
{
    private final Handler handler;

    private final Runnable removed;

    private final Executor wrapped;

    public RemoveCallbackWrapper(Handler handler, Runnable removed, Executor wrapped)
    {
        this.handler = handler;
        this.removed = removed;
        this.wrapped = wrapped;
    }

    @Override
    public void execute()
    {
        handler.removeCallbacks(removed);
        wrapped.execute();
    }
}
