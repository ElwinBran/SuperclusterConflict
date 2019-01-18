package com.github.elwinbran.android.scc.utility;

import android.arch.core.util.Function;

import com.github.elwinbran.android.scc.Board;
import com.github.elwinbran.android.scc.CardGroups;
import com.github.elwinbran.android.scc.GameNumbers;
import com.github.elwinbran.android.scc.backend.ROOMBoard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;
import com.github.elwinbran.android.scc.backend.ROOMGameNumbers;
import com.github.elwinbran.android.scc.utility.dimplementations.MappedGameNumbers;
import com.github.elwinbran.android.scc.utility.dimplementations.EmptyBoard;

import java.util.HashMap;

/**
 * Simple converter for pojo persistable boards to domain
 * equivalents.
 *
 * @author Elwin Slokker
 */
public class ROOMBoardConverter implements Function<ROOMBoard, Board>
{

    private final Function<ROOMCardGroups, CardGroups> converter = new
            ROOMCardGroupConverter();

    private final Board empty = new EmptyBoard();

    @Override
    public Board apply(ROOMBoard input)
    {
        if(input == null)
        {
            return empty;
        }
        ROOMGameNumbers pojoNumbers = input.getNumbers();
        final GameNumbers domainNumbers =
                new MappedGameNumbers(new HashMap<>(pojoNumbers.getNumbers()));
        final CardGroups domainGroups = converter.apply(input.getGroups());
        Board domainBoard = new Board() {

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
        return domainBoard;
    }
}
