package com.github.elwinbran.scc.api;

import com.github.elwinbran.android.scc.Card;

/**
 * Represents a single named group. Used for serialisation.
 *
 * @author Elwin Slokker
 */
public interface Group
{
    public Iterable<Card> cards();

    public String name();
}
