package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.utility.ROOMCardConverters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.Card} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "card")
public class ROOMCard implements Parcelable
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    private int id;

    @SerializedName("values")
    @ColumnInfo(name = "values")
    @TypeConverters(ROOMCardConverters.class)
    private Map<String, String> values;

    public ROOMCard(){}

    public ROOMCard(Parcel in)
    {
        id = in.readInt();
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
        parcel.writeString(ROOMCardConverters.stringFromMap(values));
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == null)
        {
            return false;
        }
        if(other instanceof ROOMCard)
        {
            ROOMCard otherCard = (ROOMCard) other;
            Map<String, String> otherValues = otherCard.values;
            for(String key : values.keySet())
            {
                if(!values.get(key).equals(otherValues.get(key)))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
