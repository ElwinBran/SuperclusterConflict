package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMBoard;

/**
 * TypeConverter methods for translating the {@link com.github.elwinbran.android.scc.backend.ROOMBoard} entities.
 *
 * @author Elwin Slokker
 */
public class ROOMBoardConverters
{
    @TypeConverter
    public static ROOMBoard fromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        return null;
    }

    @TypeConverter
    public static String toString(ROOMBoard value)
    {
        if(value == null)
        {
            return null;
        }
        return null;
    }
}
