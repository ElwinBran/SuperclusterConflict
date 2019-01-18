package com.github.elwinbran.android.scc.utility.dimplementations;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.CardGroups;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A simple card group implementation that utilizes a map.
 *
 * @author Elwin Slokker
 */
public class MappedCardGroups implements CardGroups
{
    private final Map<String, List<Card>> wrappedMap;

    /**
     * Primary constructor
     *
     * @param map The map to be used as card group holder.
     *            Consider using a copy to prevent mutability problems.
     */
    public MappedCardGroups(Map<String, List<Card>> map)
    {
        wrappedMap = map;
    }

    @Override
    public Iterable<Card> group(String s)
    {
        List<Card> group = wrappedMap.get(s);
        if(group == null)
        {
            return Collections.EMPTY_LIST;
        }
        return group;
    }
}
