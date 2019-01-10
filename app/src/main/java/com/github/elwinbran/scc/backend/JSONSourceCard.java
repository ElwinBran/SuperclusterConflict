package com.github.elwinbran.scc.backend;

import android.arch.core.util.Function;
import android.util.Log;
import android.util.Pair;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.StringTable;
import com.github.elwinbran.scc.utility.MapStringTable;
import com.github.elwinbran.scc.utility.PropertySplitter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A card from a JSON source.
 *
 * @author Elwin Slokker
 */
public class JSONSourceCard implements Card, Serializable
{

    private final Function<String, Pair<String, String>> splitter = new PropertySplitter('=');

    public JSONSourceCard() {}

    @SerializedName("flatProperties")
    @Expose
    private List<String> flatProperties;

    @Override
    public StringTable properties()
    {
        Map<String, String> mapp = new HashMap<>();
        for(String s : flatProperties)
        {
            Pair<String, String> pair = splitter.apply(s);
            mapp.put(pair.first, pair.second);
        }
        return new MapStringTable(mapp);
    }
}
