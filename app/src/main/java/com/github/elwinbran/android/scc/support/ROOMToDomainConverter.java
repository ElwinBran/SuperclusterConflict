package com.github.elwinbran.android.scc.support;

import android.arch.core.util.Function;

import com.github.elwinbran.android.scc.Board;
import com.github.elwinbran.android.scc.CardGroups;
import com.github.elwinbran.android.scc.GameNumbers;
import com.github.elwinbran.android.scc.GameState;
import com.github.elwinbran.android.scc.Player;
import com.github.elwinbran.android.scc.PlayerSequence;
import com.github.elwinbran.android.scc.backend.ROOMBoard;
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
        ROOMBoard pojoBoard = input.getBoardState();
        final String playerName = input.getFirstPlayer().getName();
        final Player player = new Player() {
            @Override
            public Board povBoard()
            {
                return null;
            }

            @Override
            public String name()
            {
                return playerName;
            }
        };
        final String opponentName = input.getSecondPlayer().getName();
        final Player opponent = new Player() {
            @Override
            public Board povBoard()
            {
                return null;
            }

            @Override
            public String name()
            {
                return opponentName;
            }
        };
        final PlayerSequence domainSequence = new PlayerSequence() {
            @Override
            public Player firstPlayer()
            {
                return player;
            }

            @Override
            public Player secondPlayer()
            {
                return opponent;
            }
        };

        final CardGroups domainGroups;//TODO: retrieve
        final GameNumbers domainNumbers;

        final Board newBoard = new Board() {
            @Override
            public CardGroups cards()
            {
                return domainGroups;
            }

            @Override
            public GameNumbers counters()
            {
                return domainNumbers;
            }
        };

        GameState newDomain = new GameState() {
            @Override
            public PlayerSequence playerSequence()
            {
                return domainSequence;
            }

            @Override
            public Board fullBoard()
            {
                return newBoard;
            }
        };
        return null;
    }
}
