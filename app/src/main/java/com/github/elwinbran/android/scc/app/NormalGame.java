package com.github.elwinbran.android.scc.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.elwinbran.android.scc.GameState;
import com.github.elwinbran.android.scc.api.AppDatabase;
import com.github.elwinbran.android.scc.api.GameStatisticsViewModel;
import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;
import com.github.elwinbran.android.scc.backend.ROOMGameState;
import com.github.elwinbran.android.scc.backend.RetroStatistics;
import com.github.elwinbran.android.scc.fragments.DemoCard;
import com.github.elwinbran.android.scc.support.DemoGameGenerator;
import java.util.ArrayList;
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
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        tempView = (View) dragEvent.getLocalState();
                        tempView.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
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
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        View viewT = (View) dragEvent.getLocalState();
                        ViewGroup owner = (ViewGroup) viewT.getParent();
                        owner.removeView(viewT);
                        fragmentCount--;
                        playCard(viewT);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
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
        //TODO: actually get the right fragment...
        Fragment cardFragment = getFragmentManager().findFragmentById(dragged.getId());
        playCard(cardFragment.getArguments());
        addCardButton.setClickable(true);
        addCardButton.setEnabled(true);
    }

    private void playCard(Bundle cardData)
    {
        ROOMCard pojoCard = cardData.getParcelable(getString(R.string.bundle_key));
        String rawEffect = pojoCard.getValues().get(getString(R.string.demo_effect_key));
        Map<String, List<ROOMCard>> pojoMap = pojoGameState.getBoardState().getGroups().getCardMap();
        List<ROOMCard> pojoHand = pojoMap.get(getString(R.string.demo_hand_save));
        pojoHand.remove(pojoCard);
        Integer damageKeyIndex = rawEffect.indexOf(getString(R.string.demo_simple_damage_value_key));
        if(damageKeyIndex != -1)
        {
            int index = rawEffect.indexOf(getString(R.string.demo_simple_value_separator)) + 1;
            String value = rawEffect.substring(index, rawEffect.length());
            Integer damageValue = Integer.decode(value);
            Integer newHealth =
                    pojoGameState.getBoardState().getNumbers().getNumbers().get(getString(R.string.health_key))
                            - damageValue;
            healthDisplay.setText(newHealth.toString());
            if (newHealth < 1)
            {
                Intent wonMessage = new Intent(this, DemoWonActivity.class);
                try
                {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                gameStateDB.gameStateDao().deleteEntry(pojoGameState);
                GameStatisticsViewModel model = new RetroStatistics(BuildConfig.ApiKey);
                model.incrementWins();
                startActivity(wonMessage);
            }
            else
            {
                pojoGameState.getBoardState().getNumbers().getNumbers().put(getString(R.string.health_key), newHealth);
            }
        }
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
