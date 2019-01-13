package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.utility.ROOMGameNumbersConverters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.GameNumbers} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "gameNumberMap")
public class ROOMGameNumbers implements Parcelable
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    private int id;

    @SerializedName("numbers")
    @ColumnInfo(name = "numbers")
    @TypeConverters(ROOMGameNumbersConverters.class)
    private Map<String, Integer> numbers;

    public ROOMGameNumbers(){}

    public ROOMGameNumbers(Parcel in)
    {
        id = in.readInt();
        numbers = ROOMGameNumbersConverters.fromString(in.readString());
    }

    public Map<String, Integer> getNumbers()
    {
        return this.numbers;
    }

    public void setNumbers(Map<String, Integer> newValues)
    {
        this.numbers = newValues;
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
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(ROOMGameNumbersConverters.fromStringMap(numbers));
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
