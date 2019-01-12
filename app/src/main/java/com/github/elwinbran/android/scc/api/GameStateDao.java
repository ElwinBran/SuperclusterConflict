package com.github.elwinbran.android.scc.api;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.github.elwinbran.android.scc.backend.ROOMGameState;

import java.util.List;

/**
 * The DAO interface for {@link ROOMGameState} in the ROOM database.
 * It is fully crud, even if the Demo does not use it.
 *
 * @author Elwin Slokker
 */
@Dao
public interface GameStateDao
{
    @Query("SELECT * FROM gameState")
    public LiveData<List<ROOMGameState>> getAllEntries();

    @Insert
    public void insertEntry(ROOMGameState entry);

    @Delete
    public void deleteEntry(ROOMGameState entry);

    @Update
    public void updateEntry(ROOMGameState entry);
}
