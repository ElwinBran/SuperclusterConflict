package com.github.elwinbran.android.scc.utility.dimplementations;

import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.Board;
import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.CardGroups;
import com.github.elwinbran.android.scc.GameNumbers;
import com.github.elwinbran.android.scc.PossibleInteger;

import java.util.Collections;
import java.util.Iterator;

/**
 * A board without any contents at all.
 * So it does not return null but just no cards and numbers.
 *
 * @author Elwin Slokker
 */
public class EmptyBoard implements Board
{
    private final CardGroups groups;

    private final GameNumbers numbers;

    public EmptyBoard()
    {
        groups = new CardGroups() {
            @Override
            public Iterable<Card> group(String s)
            {
                return Collections.EMPTY_LIST;
            }
        };
        final PossibleInteger empty = new DefaultPossibleInteger();
        numbers = new GameNumbers() {
            @Override
            public PossibleInteger counterValue(String s)
            {
                return empty;
            }
        };
    }

    @Override
    public CardGroups cards()
    {
        return groups;
    }

    @Override
    public GameNumbers counters()
    {
        return numbers;
    }
}
