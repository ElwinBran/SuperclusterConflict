package com.github.elwinbran.scc.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.github.elwinbran.scc.com.githhub.elwinbran.scs.support.Executor;

/**
 * Starts a single {@link Intent} in the given {@link AppCompatActivity Activity}.
 *
 * @author Elwin Slokker
 */
public class IntentStarter implements Executor
{
    private final AppCompatActivity host;

    private final Intent intent;

    public IntentStarter(AppCompatActivity host, Intent intent)
    {
        this.host = host;
        this.intent = intent;
    }

    @Override
    public void execute()
    {
        host.startActivity(intent);
    }
}
