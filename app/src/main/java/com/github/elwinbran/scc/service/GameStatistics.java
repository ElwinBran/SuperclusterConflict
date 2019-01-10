package com.github.elwinbran.scc.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.github.elwinbran.scc.backend.interfaces.Statistic;

/**
 * The game statistics behaviour.
 *
 * @author Elwin Slokker
 */
public abstract class GameStatistics extends ViewModel
{

    public abstract LiveData<Statistic> retrieve();

    public abstract void incrementWins();

    public abstract void incrementLosses();

    public abstract void reset();

}
