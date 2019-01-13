package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.github.elwinbran.android.scc.utility.ROOMCardGroupsConverters;
import com.github.elwinbran.android.scc.utility.ROOMGameNumbersConverters;
import com.google.gson.annotations.SerializedName;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.Board} in the ROOM database.
 * Also includes GSON annotations for correct parcelization in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "board")
public class ROOMBoard implements Parcelable
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    private int id;

    @SerializedName("groups")
    @ColumnInfo(name = "groups")
    @TypeConverters(ROOMCardGroupsConverters.class)
    private ROOMCardGroups groups;

    @SerializedName("numbers")
    @ColumnInfo(name = "numbers")
    @TypeConverters(ROOMGameNumbersConverters.class)
    private ROOMGameNumbers numbers;

    public ROOMBoard() {}

    public ROOMBoard(Parcel in)
    {
        id = in.readInt();
        //groups = ROOMCardGroupsConverters.fromString(in.readString());
        groups = in.readParcelable(ROOMCardGroups.class.getClassLoader());
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

    @NonNull
    public int getId()
    {
        return id;
    }

    public void setId(@NonNull int id)
    {
        this.id = id;
    }

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
        parcel.writeInt(id);
        //parcel.writeString(ROOMCardGroupsConverters.toString(groups));
        parcel.writeParcelable(groups, i);
        parcel.writeString(ROOMGameNumbersConverters.numbersToString(numbers));
    }
}
