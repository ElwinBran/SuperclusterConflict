package com.github.elwinbran.scc.backend;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.StringTable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

/**
 * A card from a JSON source.
 *
 * @author Elwin Slokker
 */
public class JSONSourceCard implements Card
{

    @SerializedName("flatProperties")
    @Expose
    private JSONArray table;

    @Override
    public StringTable properties()
    {
        return new JSONStringTable(table);
    }
}
