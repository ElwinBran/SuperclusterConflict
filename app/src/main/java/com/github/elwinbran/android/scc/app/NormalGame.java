package com.github.elwinbran.android.scc.app;

import android.os.Bundle;

import com.github.elwinbran.scc.app.R;

public class NormalGame extends FullscreenCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstance)
    {
        setContentView(R.layout.activity_normal_game);
        super.assign(findViewById(R.id.root_constraint_layout));
        super.onCreate(savedInstance);
        /*
        So basically some extra thread is required to host the gameloop.
        This is a two way system...
        gameloop requires certain input values
        then UI thread needs to be notified and apply gamestate
        */
    }


}
