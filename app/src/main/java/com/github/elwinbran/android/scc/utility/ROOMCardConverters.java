package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMCard;

import java.util.Map;

/**
 * TypeConverter methods for translating the {@link com.github.elwinbran.android.scc.backend.ROOMCard} entities.
 *
 * @author Elwin Slokker
 */
public class ROOMCardConverters
{
    @TypeConverter
    public static ROOMCard fromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        return null;
    }

    @TypeConverter
    public static String toString(ROOMCard value)
    {
        if(value == null)
        {
            return null;
        }
        return null;
    }

    @TypeConverter
    public static Map<String, String> mapFromString(String value)
    {
        if(value == null)
        {
            return null;
        }
    }

    @TypeConverter
    public static String stringFromMap(Map<String, String> value)
    {
        if(value == null)
        {
            return null;
        }
    }
}
