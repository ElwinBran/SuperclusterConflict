package com.github.elwinbran.scs.app;

import com.github.elwinbran.scs.com.githhub.elwinbran.scs.support.Executor;

/**
 * A simple runnable that executes a
 * {@link com.github.elwinbran.scs.com.githhub.elwinbran.scs.support.Executor}.
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
