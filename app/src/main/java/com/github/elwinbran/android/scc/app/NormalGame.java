package com.github.elwinbran.android.scc.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.core.util.Function;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.elwinbran.android.scc.Board;
import com.github.elwinbran.android.scc.GameState;
import com.github.elwinbran.android.scc.api.AppDatabase;
import com.github.elwinbran.android.scc.backend.ROOMBoard;
import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;
import com.github.elwinbran.android.scc.backend.ROOMGameNumbers;
import com.github.elwinbran.android.scc.backend.ROOMGameState;
import com.github.elwinbran.android.scc.backend.ROOMPlayer;
import com.github.elwinbran.android.scc.fragments.DemoCard;
import com.github.elwinbran.android.scc.support.DemoGameGenerator;
import com.github.elwinbran.android.scc.support.GameStateProperty;
import com.github.elwinbran.android.scc.support.ROOMToDomainConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The normal game activity of Supercluster Conflict.
 * TODO: Most of this class' code is WIP and meant for the demo only!
 *
 * @author Elwin Slokker
 */
public class NormalGame extends FullscreenCompatActivity
{
    private int fragmentCount = 0;

    private AppDatabase gameStateDB;

    private TextView playerNameDisplay;

    private TextView opponentNameDisplay;

    private TextView healthDisplay;

    private Button addCardButton;

    private ROOMGameState pojoGameState;

    private LinearLayout playerCardDisplayView;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        setContentView(R.layout.activity_normal_game);
        super.assign(findViewById(R.id.root_constraint_layout));
        super.onCreate(savedInstance);
        gameStateDB = AppDatabase.getInstance(this);
        playerCardDisplayView = findViewById(R.id.player_cards_view);

        playerNameDisplay = findViewById(R.id.player_name_text_view);
        opponentNameDisplay = findViewById(R.id.opponent_name_text_view);
        healthDisplay = findViewById(R.id.health_text_view);
        final LinearLayout opponentCardDisplayView = findViewById(R.id.opponent_cards_view);
        addCardButton = findViewById(R.id.add_button);

        playerCardDisplayView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent)
            {
                int action = dragEvent.getAction();
                View tempView;
                ViewGroup owner;
                LinearLayout container;
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d("none", "onDrag: enter");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d("none", "onDrag: left");
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d("none", "onDrag: end drag");
                        tempView = (View) dragEvent.getLocalState();
                        tempView.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d("none", "onDrag: dropped");
                        tempView = (View) dragEvent.getLocalState();
                        tempView.setVisibility(View.VISIBLE);
                    default:
                        break;
                }
                return true;
            }
        });
        opponentCardDisplayView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent)
            {
                int action = dragEvent.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d("none", "onDrag: oppo enter");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d("none", "onDrag: oppo left");
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d("none", "onDrag: oppo end drag");
                        View viewT = (View) dragEvent.getLocalState();
                        ViewGroup owner = (ViewGroup) viewT.getParent();
                        owner.removeView(viewT);
                        fragmentCount--;
                        playCard(viewT);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d("none", "onDrag: oppo dropped");
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onStop()
    {
        super.onStop();
        gameStateDB.gameStateDao().updateEntry(pojoGameState);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        List<ROOMGameState> states = gameStateDB.gameStateDao().getAllEntries();
        if(states == null || states.size() == 0)
        {
            ROOMGameState demoStartState = DemoGameGenerator.gameState(this);
            gameStateDB.gameStateDao().insertEntry(demoStartState);
            pojoGameState = demoStartState;
        }
        else
        {
            for (ROOMGameState state : gameStateDB.gameStateDao().getAllEntries())
            {
                if(state.getName().equals(getString(R.string.demo_game_state_name)))
                {
                    pojoGameState = state;
                }
            }
        }
        playerNameDisplay.setText(pojoGameState.getFirstPlayer().getName());
        opponentNameDisplay.setText(pojoGameState.getSecondPlayer().getName());
        String healthKey = getString(R.string.health_key);
        String healthString = pojoGameState.getBoardState().getNumbers().getNumbers().get(healthKey).toString();
        healthDisplay.setText(healthString);

        String handKey = getString(R.string.demo_hand_save);
        ROOMCardGroups demoGroups = pojoGameState.getBoardState().getGroups();
        Iterable<ROOMCard> playerHandCards = demoGroups.getCardMap().get(handKey);
        if (playerHandCards != null)
        {
            for(ROOMCard card : playerHandCards)
            {
                addFragment(card);
            }
        }
        final List<ROOMCard> demoSet = demoGroups.getCardMap().get(getString(R.string.demo_full_set_name));
        addCardButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Random randomizer = new Random();
                ROOMCard randomDemoCard = demoSet.get(randomizer.nextInt(demoSet.size()));
                addFragment(randomDemoCard);
                List<ROOMCard> handCards = pojoGameState.getBoardState().getGroups().getCardMap().get(
                        getString(R.string.demo_hand_save));
                if(handCards == null)
                {
                    handCards = new ArrayList<>();
                    pojoGameState.getBoardState().getGroups().getCardMap().put(getString(R.string.demo_hand_save), handCards);
                }
                handCards.add(randomDemoCard);
                if(fragmentCount > 3)
                {
                    addCardButton.setClickable(false);
                    addCardButton.setEnabled(false);
                }
            }
        });
    }

    private void playCard(View dragged)
    {
        Log.d("none", Integer.toString(dragged.getId()));
        Fragment cardFragment = getFragmentManager().findFragmentById(dragged.getId());
        playCard(cardFragment.getArguments());
    }

    private void playCard(Bundle cardData)
    {
        ROOMCard pojoCard = cardData.getParcelable(getString(R.string.bundle_key));

        //TODO: finish up the game code and update pojo state
    }


    /**
     * Used to update UI based on state changes.
     * The POJO-state must be updated separately before this happens.
     *
     * @param newState
     */
    private void updateUI(GameState newState)
    {
        //players
        Log.d("none", "updateUI: called!");
        //numbers

        //cards

    }

    private void addFragment(ROOMCard card)
    {
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        Fragment myFrag = new DemoCard();
        Bundle bundledCard = new Bundle();
        bundledCard.putParcelable(getString(R.string.bundle_key), card);
        myFrag.setArguments(bundledCard);
        fragTransaction.add(playerCardDisplayView.getId(), myFrag , "fragment" + Integer.toString(fragmentCount++));
        fragTransaction.commit();
    }

}
