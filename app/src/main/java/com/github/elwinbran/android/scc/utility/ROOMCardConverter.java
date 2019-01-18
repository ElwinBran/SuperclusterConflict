package com.github.elwinbran.android.scc.utility;

import android.arch.core.util.Function;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.StringTable;
import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.utility.dimplementations.DefaultCard;
import com.github.elwinbran.android.scc.utility.dimplementations.MappedStringTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Converts a pojo card into a domain card.
 *
 * @author Elwin Slokker
 */
public class ROOMCardConverter implements Function<ROOMCard, Card>
{
    @Override
    public Card apply(ROOMCard input)
    {
        Map<String, String> valueCopy = new HashMap<>(input.getValues());
        StringTable domainTable = new MappedStringTable(valueCopy);
        return new DefaultCard(domainTable);
    }
}
