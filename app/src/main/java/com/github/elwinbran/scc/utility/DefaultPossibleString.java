package com.github.elwinbran.scc.utility;

import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.PossibleString;

/**
 * A simple PossibleString that can be unsafe and is not strict.
 *
 * @author Elwin Slokker
 */
public class DefaultPossibleString implements PossibleString
{
    private final String result;

    private final Boolean present;

    /**
     * Makes the null string.
     */
    public DefaultPossibleString()
    {
        this(null, false);
    }

    /**
     * Primary constructor that sets all corresponding behaviour.
     * @param result
     * @param present
     */
    public DefaultPossibleString(String result, @NonNull Boolean present)
    {
        this.present = present;
        this.result = result;
    }

    @Override
    public String resultText()
    {
        return this.result;
    }

    @Override
    public Boolean isPresent()
    {
        return this.present;
    }
}
