package com.github.elwinbran.android.scc.support;

import android.app.Activity;

import com.github.elwinbran.android.scc.app.R;
import com.github.elwinbran.android.scc.backend.ROOMBoard;
import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;
import com.github.elwinbran.android.scc.backend.ROOMGameNumbers;
import com.github.elwinbran.android.scc.backend.ROOMGameState;
import com.github.elwinbran.android.scc.backend.ROOMPlayer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Provides the default demo game.
 *
 * @author Elwin Slokker
 */
public class DemoGameGenerator
{
    /**
     *
     * @param stringReferencer Requires calling activity to get strings.
     * @return A fully functional game state that is the initial demo state.
     */
    public static ROOMGameState gameState(Activity stringReferencer)
    {
        ROOMGameState temp = new ROOMGameState();
        temp.setName(stringReferencer.getString(R.string.demo_game_state_name));
        ROOMPlayer player = new ROOMPlayer();
        player.setName(stringReferencer.getString(R.string.demo_player_name));
        ROOMPlayer opponent = new ROOMPlayer();
        opponent.setName(stringReferencer.getString(R.string.demo_computer_opponent_name));
        temp.setFirstPlayer(player);
        temp.setSecondPlayer(opponent);

        ROOMBoard tempBoard = new ROOMBoard();
        ROOMCardGroups tempGroups = new ROOMCardGroups();
        Map<String, List<ROOMCard>> tempGroupsMap = new HashMap<>();
        List<ROOMCard> tempCardList = new LinkedList<>();
        //Cards
        ROOMCard tempCard = new ROOMCard();
        Map<String, String> tempCardValues = new HashMap<>();
        tempCardValues.put("flavor text", "Perhaps the most overlooked job of this age is gathering knowledge");
        tempCardValues.put(stringReferencer.getString(R.string.demo_effect_key), "none");
        tempCardValues.put("name", "Explorer");
        tempCard.setValues(tempCardValues);
        tempCardList.add(tempCard);

        ROOMCard secondCard = new ROOMCard();
        Map<String, String> secondCardValues = new HashMap<>();
        secondCardValues.put("flavor text", "\"Sneaky guys...\"");
        secondCardValues.put(stringReferencer.getString(R.string.demo_effect_key),
                stringReferencer.getString(R.string.demo_simple_damage_value_key) +
                        stringReferencer.getString(R.string.demo_simple_value_separator) + "1");
        secondCardValues.put("name", "Eel");
        secondCard.setValues(secondCardValues);
        tempCardList.add(secondCard);

        ROOMCard thirdCard = new ROOMCard();
        Map<String, String> thirdCardValues = new HashMap<>();
        thirdCardValues.put("flavor text", "Bullets for breakfast, victory is dinner");
        thirdCardValues.put(stringReferencer.getString(R.string.demo_effect_key),
                stringReferencer.getString(R.string.demo_simple_damage_value_key) +
                        stringReferencer.getString(R.string.demo_simple_value_separator) + "2");
        thirdCardValues.put("name", "Fighter");
        thirdCard.setValues(thirdCardValues);
        tempCardList.add(thirdCard);

        //Groups
        tempGroupsMap.put(stringReferencer.getString(R.string.demo_full_set_name), tempCardList);
        tempGroups.setCardMap(tempGroupsMap);

        //Numbers
        ROOMGameNumbers tempNumbers = new ROOMGameNumbers();
        Map<String, Integer> tempNumbersMap = new HashMap<>();
        tempNumbersMap.put(stringReferencer.getString(R.string.health_key), 10);
        tempNumbers.setNumbers(tempNumbersMap);
        tempBoard.setGroups(tempGroups);
        tempBoard.setNumbers(tempNumbers);
        temp.setBoardState(tempBoard);
        temp.setName(stringReferencer.getString(R.string.demo_game_state_name));
        return temp;
    }

}
