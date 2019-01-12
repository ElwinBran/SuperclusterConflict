package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.github.elwinbran.android.scc.utility.ROOMCardGroupsConverters;
import com.github.elwinbran.android.scc.utility.ROOMGameNumbersConverters;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.Board} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "board")
public class ROOMBoard implements Parcelable
{

    @ColumnInfo(name = "groups")
    @TypeConverters(ROOMCardGroupsConverters.class)
    private ROOMCardGroups groups;

    @ColumnInfo(name = "numbers")
    @TypeConverters(ROOMGameNumbersConverters.class)
    private ROOMGameNumbers numbers;

    public ROOMBoard(Parcel in)
    {
        groups = ROOMCardGroupsConverters.fromString(in.readString());
        numbers = ROOMGameNumbersConverters.numbersFromString(in.readString());
    }

    public static final Creator<ROOMBoard> CREATOR = new Creator<ROOMBoard>()
    {
        @Override
        public ROOMBoard createFromParcel(Parcel in)
        {
            return new ROOMBoard(in);
        }

        @Override
        public ROOMBoard[] newArray(int size)
        {
            return new ROOMBoard[size];
        }
    };

    public ROOMCardGroups getGroups()
    {
        return groups;
    }

    public void setGroups(ROOMCardGroups groups)
    {
        this.groups = groups;
    }

    public ROOMGameNumbers getNumbers()
    {
        return numbers;
    }

    public void setNumbers(ROOMGameNumbers numbers)
    {
        this.numbers = numbers;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(groups, i);
        parcel.writeParcelable(numbers, i);
    }
}
