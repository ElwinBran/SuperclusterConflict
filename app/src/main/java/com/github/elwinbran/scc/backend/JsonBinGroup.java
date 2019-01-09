package com.github.elwinbran.scc.backend;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.scc.backend.interfaces.Group;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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
    private List<Card> cards;

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
        return cards;
    }

    @Override
    public String name()
    {
        return name;
    }
}
