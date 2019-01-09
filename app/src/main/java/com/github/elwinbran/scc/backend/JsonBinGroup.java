package com.github.elwinbran.scc.backend;

import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.scc.backend.interfaces.Group;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * POJO for JsonBin results.
 *
 * @author Elwin Slokker
 */
public class JsonBinGroup implements Group, Serializable
{

    @SerializedName("cards")
    @Expose
    private List<JSONSourceCard> cards;

    @SerializedName("name")
    @Expose
    private String name;

    public JsonBinGroup(){}

    public JsonBinGroup(String name, List<Card> cards)
    {

    }

    @Override
    public Iterable<Card> cards()
    {
        return new Iterable<Card>() {
            @NonNull
            @Override
            public Iterator iterator()
            {
                return cards.iterator();
            }
        };
    }

    @Override
    public String name()
    {
        return name;
    }
}
