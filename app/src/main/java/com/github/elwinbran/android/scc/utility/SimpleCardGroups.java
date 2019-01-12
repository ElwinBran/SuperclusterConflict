package com.github.elwinbran.android.scc.utility;

import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.CardGroups;

import java.util.Iterator;

/**
 * Represents on a single simple card group.
 *
 * @author Elwin Slokker
 */
public class SimpleCardGroups implements CardGroups
{
    private final String name;

    private final Iterable<Card> cards;

    public SimpleCardGroups(String name, Iterable<Card> cards)
    {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public Iterable<Card> group(String s)
    {
        if(s.equals(name))
        {
            return cards;
        }
        else
        {
            return new Iterable(){
                @NonNull
                @Override
                public Iterator iterator()
                {
                    return new Iterator() {
                        @Override
                        public boolean hasNext()
                        {
                            return false;
                        }

                        @Override
                        public Object next()
                        {
                            return null;
                        }
                    };
                }
            };
        }
    }
}
