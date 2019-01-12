package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.GameNumbers} in the ROOM database.
 *
 * @author Elwin Slokker
 */
public class ROOMGameNumbers implements Parcelable
{

    @ColumnInfo(name = "numbers")
    private Map<String, Integer> numbers;

    public ROOMGameNumbers(){}

    public ROOMGameNumbers(Parcel in)
    {

    }

    public Map<String, Integer> getNumbers()
    {
        return this.numbers;
    }

    public void setNumbers(Map<String, Integer> newValues)
    {
        this.numbers = newValues;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {

    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<ROOMGameNumbers> CREATOR = new Creator<ROOMGameNumbers>()
    {
        @Override
        public ROOMGameNumbers createFromParcel(Parcel in)
        {
            return new ROOMGameNumbers(in);
        }

        @Override
        public ROOMGameNumbers[] newArray(int size)
        {
            return new ROOMGameNumbers[size];
        }
    };
}
