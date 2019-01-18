package com.github.elwinbran.android.scc.support;

import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.GameState;

import java.util.Observable;

/**
 * A property like object that informs listeners of its change because it is also an
 * observable.
 *
 * @author Elwin Slokker
 */
public class GameStateProperty extends Observable
{

    private GameState property;

    /**
     * Primary constructor.
     *
     * @param initial The first game state to be contained.
     */
    public GameStateProperty(GameState initial)
    {
        this.property = initial;
    }

    public GameState currentState()
    {
        return property;
    }

    public void replace(GameState newState)
    {
        this.property = newState;
        super.setChanged();
        synchronized(this)
        {
            this.notify();
        }
        //super.notifyObservers(newState);
    }
}
