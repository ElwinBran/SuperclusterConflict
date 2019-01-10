package com.github.elwinbran.scc.utility;

import android.arch.core.util.Function;
import android.util.Pair;


/**
 * A function for splitting property or key-value like strings into object pairs.
 *
 * @author Elwin Slokker
 */
public class PropertySplitter implements Function<String, Pair<String, String>>
{

    private final Character splitSymbol;

    public PropertySplitter(Character splitSymbol)
    {
        this.splitSymbol = splitSymbol;
    }

    @Override
    public Pair<String, String> apply(String s)
    {
        int splitIndex = s.indexOf(splitSymbol);
        String key = s.substring(0, splitIndex);
        String value = s.substring(splitIndex + 1, s.length() - 1);
        return new Pair<>(key, value);
    }
}
