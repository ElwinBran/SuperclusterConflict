package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.utility.ROOMCardGroupsConverters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.CardGroups} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "cardGroup")
public class ROOMCardGroups implements Parcelable
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    private int id;

    @SerializedName("cardMap")
    @ColumnInfo(name = "cardMap")
    @TypeConverters(ROOMCardGroupsConverters.class)
    private Map<String,Iterable<ROOMCard>> cardMap;

    public ROOMCardGroups(){}

    public ROOMCardGroups(Parcel in)
    {
        id = in.readInt();
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

    public Map<String, Iterable<ROOMCard>> getCardMap()
    {
        return cardMap;
    }

    public void setCardMap(Map<String, Iterable<ROOMCard>> cardMap)
    {
        this.cardMap = cardMap;
    }

    @NonNull
    public int getId()
    {
        return id;
    }

    public void setId(@NonNull int id)
    {
        this.id = id;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(id);
        parcel.writeString(ROOMCardGroupsConverters.stringFromMap(cardMap));
    }
}
