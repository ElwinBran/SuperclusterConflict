package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;

import java.util.Map;

/**
 * TypeConverter methods for translating the {@link com.github.elwinbran.android.scc.backend.ROOMCardGroups} entities.
 *
 * @author Elwin Slokker
 */
public class ROOMCardGroupsConverters
{

    @TypeConverter
    public static ROOMCardGroups fromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        return null;
    }

    @TypeConverter
    public static String toString(ROOMCardGroups value)
    {
        if(value == null)
        {
            return null;
        }
        return null;
    }

    @TypeConverter
    public static Map<String,Iterable<ROOMCard>> mapFromString(String value)
    {
        if(value == null)
        {
            return null;
        }
    }

    @TypeConverter
    public static String stringFromMap(Map<String,Iterable<ROOMCard>> value)
    {
        if(value == null)
        {
            return null;
        }
    }
}
