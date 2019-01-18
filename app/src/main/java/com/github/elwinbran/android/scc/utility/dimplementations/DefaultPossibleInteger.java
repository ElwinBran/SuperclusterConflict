package com.github.elwinbran.android.scc.utility.dimplementations;

import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.PossibleInteger;

/**
 * Simple implementation for a null safe integer result.
 *
 * @author Elwin Slokker
 */
public class DefaultPossibleInteger implements PossibleInteger
{
    private final Integer result;

    private final Boolean present;

    /**
     * Makes the null string.
     */
    public DefaultPossibleInteger()
    {
        this(null, false);
    }

    /**
     * Primary constructor that sets all corresponding behaviour.
     * @param result
     * @param present
     */
    public DefaultPossibleInteger(Integer result, @NonNull Boolean present)
    {
        this.present = present;
        this.result = result;
    }

    @Override
    public Integer number()
    {
        return result;
    }

    @Override
    public Boolean isPresent()
    {
        return present;
    }
}
