package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.github.elwinbran.android.scc.utility.ROOMCardGroupsConverters;

import java.util.Map;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.CardGroups} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "cardGroup")
public class ROOMCardGroups implements Parcelable
{

    @ColumnInfo(name = "cardMap")
    @TypeConverters(ROOMCardGroupsConverters.class)
    private Map<String,Iterable<ROOMCard>> cardMap;

    public ROOMCardGroups(){}

    public ROOMCardGroups(Parcel in)
    {
        cardMap = ROOMCardGroupsConverters.mapFromString(in.readString());
    }

    public static final Creator<ROOMCardGroups> CREATOR = new Creator<ROOMCardGroups>()
    {
        @Override
        public ROOMCardGroups createFromParcel(Parcel in)
        {
            return new ROOMCardGroups(in);
        }

        @Override
        public ROOMCardGroups[] newArray(int size)
        {
            return new ROOMCardGroups[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(ROOMCardGroupsConverters.stringFromMap(cardMap));
    }
}
