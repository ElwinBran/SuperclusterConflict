package com.github.elwinbran.android.scc.support;

import android.arch.core.util.Function;

import com.github.elwinbran.android.scc.GameState;
import com.github.elwinbran.android.scc.backend.ROOMGameState;

/**
 * Converts a ROOM POJO to a true domain object.
 *
 * @author Elwin Slokker
 */
public class ROOMToDomainConverter implements Function<ROOMGameState, GameState>
{
    @Override
    public GameState apply(ROOMGameState input)
    {
        return null;
    }
}
