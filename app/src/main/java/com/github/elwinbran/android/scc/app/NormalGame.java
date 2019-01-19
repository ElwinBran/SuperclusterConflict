package com.github.elwinbran.android.scc.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.core.util.Function;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.elwinbran.android.scc.Board;
import com.github.elwinbran.android.scc.GameState;
import com.github.elwinbran.android.scc.api.AppDatabase;
import com.github.elwinbran.android.scc.app.R;
import com.github.elwinbran.android.scc.backend.ROOMBoard;
import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;
import com.github.elwinbran.android.scc.backend.ROOMGameNumbers;
import com.github.elwinbran.android.scc.backend.ROOMGameState;
import com.github.elwinbran.android.scc.backend.ROOMPlayer;
import com.github.elwinbran.android.scc.fragments.DemoCard;
import com.github.elwinbran.android.scc.support.GameStateProperty;
import com.github.elwinbran.android.scc.support.ROOMToDomainConverter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

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

    private TextView playerNameDisplay;

    private TextView opponentNameDisplay;

    private ROOMGameState pojoGameState;

    private Function<ROOMGameState, GameState> domainTransformer;

    private LinearLayout playerCardDisplayView;

    @Override
    public void onCreate(Bundle savedInstance)
    {
        setContentView(R.layout.activity_normal_game);
        super.assign(findViewById(R.id.root_constraint_layout));
        super.onCreate(savedInstance);
        gameStateDB = AppDatabase.getInstance(this);
        playerCardDisplayView = findViewById(R.id.player_cards_view);
        domainTransformer = new ROOMToDomainConverter();

        playerNameDisplay = findViewById(R.id.player_name_text_view);
        opponentNameDisplay = findViewById(R.id.opponent_name_text_view);
        final LinearLayout opponentCardDisplayView = findViewById(R.id.opponent_cards_view);
        final Button addCardButton = findViewById(R.id.add_button);
        addCardButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addFragment();
            }
        });


        ROOMGameState temp = new ROOMGameState();
        temp.setName(getString(R.string.demo_game_state_name));
        ROOMPlayer player = new ROOMPlayer();
        player.setName(getString(R.string.demo_player_name));
        ROOMPlayer opponent = new ROOMPlayer();
        player.setName(getString(R.string.demo_computer_opponent_name));

        ROOMBoard tempBoard = new ROOMBoard();
        ROOMCardGroups tempGroups = new ROOMCardGroups();
        Map<String, List<ROOMCard>> tempGroupsMap = new HashMap<>();
        List<ROOMCard> tempCardList = new LinkedList<>();
        //Cards
        ROOMCard tempCard = new ROOMCard();
        Map<String, String> tempCardValues = new HashMap<>();
        tempCardValues.put("name", "Explorer");
        tempCard.setValues(tempCardValues);
        tempCardList.add(tempCard);

        //Groups
        tempGroupsMap.put("hand", tempCardList);
        tempGroups.setCardMap(tempGroupsMap);

        //Numbers
        ROOMGameNumbers tempNumbers = new ROOMGameNumbers();
        Map<String, Integer> tempNumbersMap = new HashMap<>();
        tempNumbersMap.put(getString(R.string.health_key), 2);
        tempNumbers.setNumbers(tempNumbersMap);
        tempBoard.setGroups(tempGroups);
        tempBoard.setNumbers(tempNumbers);
        temp.setBoardState(tempBoard);
        gameStateDB.gameStateDao().updateEntry(temp);
        Log.d("none", gameStateDB.gameStateDao().getAllEntries().get(0).getName());
        Log.d("none", gameStateDB.gameStateDao().getAllEntries().get(0).getBoardState().getGroups()
                .getCardMap().get("hand").get(0).getValues().get("name"));

        /*
        So basically some extra thread is required to host the gameloop.
        This is a two way system...
        gameloop requires certain input values
        then UI thread needs to be notified and apply gamestate
        */
        Log.d("none", "observer created");
        state.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object o)
            {
                try
                {
                    String pName = state.currentState().playerSequence().firstPlayer().name();
                    Log.d("none", "name: " + ((pName == null)? "":pName));
                    playerNameDisplay.setText(pName);
                    opponentNameDisplay.setText(state.currentState().playerSequence().secondPlayer().name());
                    updateUI(state.currentState());
                }
                catch (Throwable ex)
                {
                    Log.e("none", "update could not be called:", ex);
                }
            }
        });
    }

    @Override
    public void onStop()
    {
        super.onStop();
        gameStateDB.gameStateDao().updateEntry(pojoGameState);
        //TODO: notice of completed save?
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d("none", "onStart: done");
        pojoGameState = gameStateDB.gameStateDao().getAllEntries().get(0);
        this.state.replace(domainTransformer.apply(pojoGameState));
        playerNameDisplay.setText(state.currentState().playerSequence().firstPlayer().name());
        opponentNameDisplay.setText(state.currentState().playerSequence().secondPlayer().name());
    }

    private int fragmentCount = 0;

    private void updateUI(GameState newState)
    {
        //players

        //numbers

        //cards

    }

    private void addFragment()
    {
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        Fragment myFrag = new DemoCard();//TODO: add model card somehow, likely through bundle arguments
        Bundle bundledCard = new Bundle();
        ROOMCard temp =
                gameStateDB.gameStateDao().getAllEntries().get(0).getBoardState().getGroups().getCardMap().get("hand").get(0);
        bundledCard.putParcelable(getString(R.string.bundle_key), temp);
        myFrag.setArguments(bundledCard);
        fragTransaction.add(playerCardDisplayView.getId(), myFrag , "fragment" + Integer.toString(fragmentCount++));
        fragTransaction.commit();
        ViewGroup.LayoutParams params = playerCardDisplayView.getLayoutParams();
        params.height = 0;
        params.width = ViewGroup.LayoutParams.FILL_PARENT;
        playerCardDisplayView.setLayoutParams(params);
    }

}
