package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMBoard;
import com.github.elwinbran.android.scc.backend.ROOMCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
        Type type =
                new TypeToken<ROOMCard>(){}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String toString(ROOMCard value)
    {
        if(value == null)
        {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static Map<String, String> mapFromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        Type type =
                new TypeToken<Map<String, String>>(){}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String stringFromMap(Map<String, String> value)
    {
        if(value == null)
        {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
