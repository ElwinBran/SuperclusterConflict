package com.github.elwinbran.android.scc.utility;

import android.arch.persistence.room.TypeConverter;

import com.github.elwinbran.android.scc.backend.ROOMBoard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

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
        Type type =
                new TypeToken<ROOMBoard>(){}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String toString(ROOMBoard value)
    {
        if(value == null)
        {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
