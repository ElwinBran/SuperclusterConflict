package com.github.elwinbran.scc.utility;

import com.github.elwinbran.android.scc.PossibleString;
import com.github.elwinbran.android.scc.StringTable;

import java.util.Map;

/**
 * A simple table implementation that utilizes a map.
 * Is mutable.
 *
 * @author Elwin Slokker
 */
public class MapStringTable implements StringTable
{

    private final Map<String, String> strings;

    /**
     * Primary constructor.
     *
     * @param strings The map to use as table. It is not copied and thus beware for
     *                mutation.
     */
    public MapStringTable(Map<String, String> strings)
    {
        this.strings = strings;
    }

    @Override
    public PossibleString entry(String s)
    {
        String result = strings.get(s);
        if(result == null)
        {
            return new DefaultPossibleString();
        }
        else
        {
            return new DefaultPossibleString(result, true);
        }
    }
}
