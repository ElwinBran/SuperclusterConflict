package com.github.elwinbran.android.scc.backend;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.Player} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "player")
public class ROOMPlayer implements Parcelable
{
    @PrimaryKey
    @NonNull
    private String name;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name  = name;
    }

    public ROOMPlayer(){}

    public ROOMPlayer(Parcel in)
    {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<ROOMPlayer> CREATOR = new Creator<ROOMPlayer>()
    {
        @Override
        public ROOMPlayer createFromParcel(Parcel in)
        {
            return new ROOMPlayer(in);
        }

        @Override
        public ROOMPlayer[] newArray(int size)
        {
            return new ROOMPlayer[size];
        }
    };
}
