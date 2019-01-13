package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.github.elwinbran.android.scc.backend.ROOMCardGroups;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
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
        Type type =
                new TypeToken<ROOMCardGroups>(){}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String toString(ROOMCardGroups value)
    {
        if(value == null)
        {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static Map<String, List<ROOMCard>> mapFromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        Type type =
                new TypeToken<Map<String, List<ROOMCard>>>(){}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String stringFromMap(Map<String, List<ROOMCard>> value)
    {
        if(value == null)
        {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
