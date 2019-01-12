package com.github.elwinbran.android.scc.app;

import com.github.elwinbran.android.scc.support.Executor;

/**
 * A simple runnable that executes a
 * {@link Executor}.
 *
 * @author Elwin Slokker
 */
public class SimpleExecutorRunner implements Runnable
{
    private final Executor executable;

    public SimpleExecutorRunner(Executor executable)
    {
        this.executable = executable;
    }

    @Override
    public void run()
    {
        executable.execute();
    }
}
