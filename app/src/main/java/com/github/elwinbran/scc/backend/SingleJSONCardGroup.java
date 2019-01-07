package com.github.elwinbran.scc.backend;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.CardGroups;

import java.io.Serializable;

/**
 * Annotated POJO used by retrofit to represent all card groups from SuperclusterConflict.
 *
 * @author Elwin Slokker
 */
public class SingleJSONCardGroup implements CardGroups, Serializable
{

    public static final String STANDARD_GROUP_NAME = "Demo";

    public SingleJSONCardGroup(){}

    /**
     *
     * @param cardGroups Must contain a group with the following name {@link #STANDARD_GROUP_NAME}.
     */
    public SingleJSONCardGroup(CardGroups cardGroups)
    {

    }

    /**
     *
     * @param s Only {@link #STANDARD_GROUP_NAME} is allowed.
     * @return
     */
    @Override
    public Iterable<Card> group(String s)
    {
        return null;
    }
}
