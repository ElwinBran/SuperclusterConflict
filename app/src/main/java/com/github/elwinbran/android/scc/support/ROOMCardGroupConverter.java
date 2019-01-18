package com.github.elwinbran.android.scc.support;

import android.arch.core.util.Function;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.CardGroups;
import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;
import com.github.elwinbran.android.scc.utility.dimplementations.MappedCardGroups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converter from pojo CardGroups to domain card groups.
 *
 * @author Elwin Slokker
 */
public class ROOMCardGroupConverter implements Function<ROOMCardGroups, CardGroups>
{
    private final Function<ROOMCard, Card> converter = new ROOMCardConverter();

    @Override
    public CardGroups apply(ROOMCardGroups input)
    {
        Map<String, List<ROOMCard>> pojoGroupsMap = input.getCardMap();
        Map<String, List<Card>> mapGroups = new HashMap<>();
        for(String key : pojoGroupsMap.keySet())
        {
            List<ROOMCard> pojoGroup = pojoGroupsMap.get(key);
            List<Card> converted;
            if(pojoGroup == null)
            {
                converted = Collections.EMPTY_LIST;
            }
            else
            {
                converted = new ArrayList<>();
                for(ROOMCard pojoCard : pojoGroup)
                {
                    converted.add(converter.apply(pojoCard));
                }
            }
            mapGroups.put(key, converted);
        }
        return new MappedCardGroups(mapGroups);
    }
}
