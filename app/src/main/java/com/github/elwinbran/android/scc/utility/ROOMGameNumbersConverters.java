package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMGameNumbers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * TypeConverter methods for translating the {@link com.github.elwinbran.android.scc.backend.ROOMGameNumbers} entities.
 *
 * @author Elwin Slokker
 */
public class ROOMGameNumbersConverters
{
    @TypeConverter
    public static ROOMGameNumbers numbersFromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        ROOMGameNumbers newNumbers = new ROOMGameNumbers();
        newNumbers.setNumbers(fromString(value));
        return newNumbers;
    }

    @TypeConverter
    public static String numbersToString(ROOMGameNumbers value)
    {
        if(value == null)
        {
            return null;
        }
        return fromStringMap(value.getNumbers());
    }

    @TypeConverter
    public static Map<String, Integer> fromString(String value)
    {
        if(value == null)
        {
            return null;
        }
        Type mapType =
                new TypeToken<Map<String, Integer>>(){}.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromStringMap(Map<String, Integer> value)
    {
        if(value == null)
        {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
