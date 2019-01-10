package com.github.elwinbran.scc.backend.interfaces;

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
