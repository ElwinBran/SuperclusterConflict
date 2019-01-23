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
    private AppDatabase gameStateDB;

    private final GameStateProperty state = new GameStateProperty(null);

    private TextView playerNameDisplay;

    private TextView opponentNameDisplay;

    private TextView healthDisplay;

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
        healthDisplay = findViewById(R.id.health_text_view);
        final LinearLayout opponentCardDisplayView = findViewById(R.id.opponent_cards_view);
        final Button addCardButton = findViewById(R.id.add_button);



        //TODO: get rid of temp data stuff
        ROOMGameState temp = new ROOMGameState();
        temp.setName(getString(R.string.demo_game_state_name));
        ROOMPlayer player = new ROOMPlayer();
        player.setName(getString(R.string.demo_player_name));
        ROOMPlayer opponent = new ROOMPlayer();
        opponent.setName(getString(R.string.demo_computer_opponent_name));
        temp.setFirstPlayer(player);
        temp.setSecondPlayer(opponent);

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
        //playerNameDisplay.requestFocus();
        ROOMCardGroups demoGroups = gameStateDB.gameStateDao().getAllEntries().get(0).getBoardState().getGroups();
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
        playerCardDisplayView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent)
            {
                int action = dragEvent.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        //view.setBackgroundDrawable(enterShape);
                        Log.d("none", "onDrag: enter");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        //view.setBackgroundDrawable(normalShape);
                        Log.d("none", "onDrag: left");
                        break;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        View tempView = (View) dragEvent.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) view;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        //view.setBackgroundDrawable(normalShape);
                        Log.d("none", "onDrag: dropped");
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

        pojoGameState = gameStateDB.gameStateDao().getAllEntries().get(0);
        this.state.replace(domainTransformer.apply(pojoGameState));
        playerNameDisplay.setText(state.currentState().playerSequence().firstPlayer().name());
        opponentNameDisplay.setText(state.currentState().playerSequence().secondPlayer().name());
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

    }

    private int fragmentCount = 0;

    private void updateUI(GameState newState)
    {
        //players

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
