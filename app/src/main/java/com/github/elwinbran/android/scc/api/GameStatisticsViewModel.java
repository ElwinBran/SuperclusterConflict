package com.github.elwinbran.android.scc.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

/**
 * The game statistics behaviour.
 *
 * @author Elwin Slokker
 */
public abstract class GameStatisticsViewModel extends ViewModel
{

    /**
     *
     * @return A special model that updates automatically.
     */
    public abstract LiveData<Statistic> retrieve();

    /**
     * Adds another 'win' to the count.
     */
    public abstract void incrementWins();

    /**
     * Adds another 'loss' to the count.
     */
    public abstract void incrementLosses();

    /**
     * Puts both the 'wins' and 'losses' to zero, effectively resetting.
     */
    public abstract void reset();

}
