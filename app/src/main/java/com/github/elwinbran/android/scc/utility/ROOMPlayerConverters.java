package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMPlayer;

/**
 * TypeConverter methods for translating the {@link ROOMPlayer} entities.
 *
 * @author Elwin Slokker
 */
public class ROOMPlayerConverters
{
    @TypeConverter
    public static ROOMPlayer fromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        else
        {
            ROOMPlayer player = new ROOMPlayer();
            player.setName(value);
            return player;
        }
    }

    @TypeConverter
    public static String playerToString(ROOMPlayer value)
    {
        if(value == null)
        {
            return null;
        }
        return value.getName();
    }
}
