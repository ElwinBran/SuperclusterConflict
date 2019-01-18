package com.github.elwinbran.android.scc.utility.dimplementations;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.StringTable;

/**
 * Default domain card implementation.
 *
 * @author Elwin Slokker
 */
public class DefaultCard implements Card
{
    final private StringTable table;

    /**
     * Primary constructor.
     *
     * @param table The values of the card.
     */
    public DefaultCard(StringTable table)
    {
        this.table = table;
    }

    @Override
    public StringTable properties()
    {
        return table;
    }
}
