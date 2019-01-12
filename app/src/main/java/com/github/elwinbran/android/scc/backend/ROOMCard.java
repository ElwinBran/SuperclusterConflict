package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.github.elwinbran.android.scc.utility.ROOMCardConverters;

import java.util.Map;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.Card} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "card")
public class ROOMCard implements Parcelable
{

    @ColumnInfo(name = "values")
    @TypeConverters(ROOMCardConverters.class)
    private Map<String, String> values;

    public ROOMCard(){}

    public ROOMCard(Parcel in)
    {
        values = ROOMCardConverters.mapFromString(in.readString());
    }

    public static final Creator<ROOMCard> CREATOR = new Creator<ROOMCard>()
    {
        @Override
        public ROOMCard createFromParcel(Parcel in)
        {
            return new ROOMCard(in);
        }

        @Override
        public ROOMCard[] newArray(int size)
        {
            return new ROOMCard[size];
        }
    };

    public Map<String, String> getValues()
    {
        return values;
    }

    public void setValues(Map<String, String> values)
    {
        this.values = values;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(ROOMCardConverters.stringFromMap(values));
    }
}
