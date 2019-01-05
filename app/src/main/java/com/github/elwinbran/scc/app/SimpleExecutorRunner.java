package com.github.elwinbran.scc.app;

import com.github.elwinbran.scc.com.githhub.elwinbran.scs.support.Executor;

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
