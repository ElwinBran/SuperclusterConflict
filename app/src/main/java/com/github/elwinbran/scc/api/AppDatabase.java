package com.github.elwinbran.scc.api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.github.elwinbran.android.scc.GameState;
import com.github.elwinbran.scc.backend.ROOMGameState;
import com.github.elwinbran.scc.backend.ROOMPlayer;

/**
 * The singleton class that provides the ROOM database class.
 * At the same time it contains the interface of the database class.
 * Use this interface when CRUD actions on a gamestate are required.
 *
 * @author Elwin Slokker
 */
@Database(entities = {ROOMGameState.class, ROOMPlayer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{

    /**
     * Name of the Database.
     */
    private final static String NAME_DATABASE = "game_state_db";

    //Static instance
    private static AppDatabase sInstance;

    /**
     * @return Retrieves a DAO that provides access to the ROOM database entities.
     */
    public abstract GameStateDao gameStateDao();

    /**
     * Use this method to retrieve the only Database object.
     *
     * @param context ?
     * @return A fully functional {@code AppDatabase} object.
     */
    public static AppDatabase getInstance(Context context)
    {
        if(sInstance == null)
        {
            sInstance = Room.databaseBuilder(context, AppDatabase.class,
                    NAME_DATABASE).allowMainThreadQueries().build();
        }
        return sInstance;
    }
}
