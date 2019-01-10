package com.github.elwinbran.scc.backend;

import com.github.elwinbran.android.scc.PossibleString;
import com.github.elwinbran.android.scc.StringTable;
import com.github.elwinbran.scc.utility.DefaultPossibleString;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Effectively converts a JSON array object to a map to use as table.
 *
 * @author Elwin Slokker
 */
public class JSONStringTable implements StringTable
{

    private final Map<String, String> strings = new HashMap<>();

    public JSONStringTable(JSONObject rawTable)
    {

        for (int i = 0; i < rawTable.length(); i++)
        {
            try
            {
                JSONObject item = rawTable.getJSONObject("stub!");
                String key = item.keys().next();
                String value = item.getString(key);
                strings.put(key, value);
            }
            catch (JSONException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public JSONStringTable(JSONArray rawTable)
    {
        for (int i = 0; i < rawTable.length(); i++)
        {
            try
            {
                JSONObject item = rawTable.getJSONObject(i);
                String key = item.keys().next();
                String value = item.getString(key);
                strings.put(key, value);
            }
            catch (JSONException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public PossibleString entry(String s)
    {
        String result = strings.get(s);
        PossibleString uncertain;
        if(result == null)
        {
            uncertain = new DefaultPossibleString();
        }
        else
        {
            uncertain = new DefaultPossibleString(result, true);
        }
        return uncertain;
    }
}
