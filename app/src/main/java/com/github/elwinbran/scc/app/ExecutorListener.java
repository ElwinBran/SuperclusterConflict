package com.github.elwinbran.scc.app;

import android.view.View;

import com.github.elwinbran.scc.com.githhub.elwinbran.scs.support.Executor;

/**
 * A listener that only executes the given
 * {@link Executor} on the action.
 *
 * @author Elwin Slokker
 */
public class ExecutorListener implements View.OnClickListener
{
    private final Executor executable;

    public ExecutorListener(Executor executable)
    {
        this.executable = executable;
    }

    @Override
    public void onClick(View view)
    {
        executable.execute();
    }
}
