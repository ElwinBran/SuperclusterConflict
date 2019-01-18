package com.github.elwinbran.android.scc.utility.dimplementations;

import com.github.elwinbran.android.scc.PossibleString;
import com.github.elwinbran.android.scc.StringTable;
import com.github.elwinbran.android.scc.utility.DefaultPossibleString;

import java.util.Map;

/**
 * Uses a map to implement a string table.
 *
 * @author Elwin Slokker
 */
public class MappedStringTable implements StringTable
{
    final private Map<String, String> wrappedMap;

    final private PossibleString empty = new DefaultPossibleString(null, false);

    public MappedStringTable(Map<String, String> map)
    {
        this.wrappedMap = map;
    }

    @Override
    public PossibleString entry(String s)
    {
        String result = wrappedMap.get(s);
        if(result == null)
        {
            return empty;
        }
        return new DefaultPossibleString(result, true);
    }
}
