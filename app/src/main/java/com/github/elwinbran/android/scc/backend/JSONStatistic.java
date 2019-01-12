package com.github.elwinbran.android.scc.backend;

import java.io.Serializable;

import com.github.elwinbran.android.scc.api.Statistic;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The GSON POJO for Retrofit statistics retrieval.
 *
 * @author Elwin Slokker
 */
public class JSONStatistic implements Serializable, Statistic
{

    @Expose
    @SerializedName("wins")
    private int wins;

    @Expose
    @SerializedName("losses")
    private int losses;

    public JSONStatistic() {}

    @Override
    public Long wins()
    {
        return (long)(this.wins);
    }

    @Override
    public Long losses()
    {
        return (long)(this.losses);
    }
}
