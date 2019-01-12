package com.github.elwinbran.scc.backend;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.github.elwinbran.scc.utility.ROOMPlayerConverters;

/**
 * Used as replacement for {@link com.github.elwinbran.android.scc.GameState} in the ROOM database.
 *
 * @author Elwin Slokker
 */
@Entity(tableName = "gameState")
public class ROOMGameState implements Parcelable
{

    @PrimaryKey
    @NonNull
    private String name;

    @ColumnInfo(name = "firstPlayer")
    @TypeConverters(ROOMPlayerConverters.class)
    private ROOMPlayer firstPlayer;

    @ColumnInfo(name = "secondPlayer")
    @TypeConverters(ROOMPlayerConverters.class)
    private ROOMPlayer secondPlayer;


    public ROOMGameState(){}

    public ROOMGameState(Parcel in)
    {
        name = in.readString();
        firstPlayer = ROOMPlayerConverters.fromString(in.readString());
        secondPlayer = ROOMPlayerConverters.fromString(in.readString());
    }

    public static final Creator<ROOMGameState> CREATOR = new Creator<ROOMGameState>()
    {
        @Override
        public ROOMGameState createFromParcel(Parcel in)
        {
            return new ROOMGameState(in);
        }

        @Override
        public ROOMGameState[] newArray(int size)
        {
            return new ROOMGameState[size];
        }
    };


    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ROOMPlayer getFirstPlayer()
    {
        return this.firstPlayer;
    }

    public void setFirstPlayer(ROOMPlayer player)
    {
        this.firstPlayer = player;
    }

    public ROOMPlayer getSecondPlayer()
    {
        return this.secondPlayer;
    }

    public void setSecondPlayer(ROOMPlayer player)
    {
        this.secondPlayer = player;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
    }
}
