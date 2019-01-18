package com.github.elwinbran.android.scc.utility.dimplementations;

import com.github.elwinbran.android.scc.GameNumbers;
import com.github.elwinbran.android.scc.PossibleInteger;

import java.util.Map;

/**
 * The numeric values of a boardgame that are global.
 *
 * @author Elwin Slokker
 */
public class MappedGameNumbers implements GameNumbers
{
    private final Map<String, Integer> wrappedMap;

    /**
     *  Primary constructor.
     *
     * @param map The underlying map used for the number values.
     *            Consider passing a copy to prevent unexpected behaviour.
     *            (this implementation does not copy maps.)
     */
    public MappedGameNumbers(Map<String, Integer> map)
    {
        wrappedMap = map;
    }

    @Override
    public PossibleInteger counterValue(String s)
    {
        Integer result = wrappedMap.get(s);
        Boolean present = (result == null)? false : true;
        return new DefaultPossibleInteger(result, present);
    }
}
