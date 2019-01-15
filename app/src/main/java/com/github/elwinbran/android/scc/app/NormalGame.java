package com.github.elwinbran.android.scc.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.elwinbran.android.scc.api.AppDatabase;
import com.github.elwinbran.android.scc.app.R;

/**
 * The normal game activity of Supercluster Conflict.
 * TODO: Most of this class' code is WIP and meant for the demo only!
 *
 * @author Elwin Slokker
 */
public class NormalGame extends FullscreenCompatActivity
{
    private AppDatabase gameStateDB;

    private final GameStateProperty state = new GameStateProperty(null);

    @Override
    public void onCreate(Bundle savedInstance)
    {
        setContentView(R.layout.activity_normal_game);
        super.assign(findViewById(R.id.root_constraint_layout));
        super.onCreate(savedInstance);
        gameStateDB = AppDatabase.getInstance(this);
        final LinearLayout playerCardDisplayView = findViewById(R.id.player_cards_view);
        final Button addCardButton = findViewById(R.id.add_button);

        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        Fragment myFrag = new DemoCard();
        fragTransaction.add(playerCardDisplayView.getId(), myFrag , "fragment");
        fragTransaction.commit();

        /*
        So basically some extra thread is required to host the gameloop.
        This is a two way system...
        gameloop requires certain input values
        then UI thread needs to be notified and apply gamestate
        */
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    private void addFragment()
    {
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        //Fragment myFrag = new ImageFragment();
        //fragTransaction.add(rowLayout.getId(), myFrag , "fragment" + fragCount);
        fragTransaction.commit();
    }

}
