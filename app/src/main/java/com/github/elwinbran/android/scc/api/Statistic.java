package com.github.elwinbran.android.scc.api;

/**
 * Simple statistic entity.
 *
 * @author Elwin Slokker
 */
public interface Statistic
{
    /**
     * @return Amount of time player has won.
     */
    public Long wins();

    /**
     *
     * @return Amount of time player has lost.
     */
    public Long losses();
}
