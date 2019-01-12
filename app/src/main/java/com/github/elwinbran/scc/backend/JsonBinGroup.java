package com.github.elwinbran.scc.backend;

import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.scc.api.Group;
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
    private final boolean fromJson;

    @SerializedName("cards")
    @Expose
    private List<JSONSourceCard> cards;

    private Iterable<Card> objectCards;

    @SerializedName("name")
    @Expose
    private String name;

    public JsonBinGroup()
    {
        this.fromJson = true;
    }

    public JsonBinGroup(String name, Iterable<Card> cards)
    {
        this.name = name;
        this.fromJson = false;
        this.objectCards = cards;
    }

    @Override
    public Iterable<Card> cards()
    {
        if(fromJson)
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
        else
        {
            return this.objectCards;
        }
    }

    @Override
    public String name()
    {
        return name;
    }
}
