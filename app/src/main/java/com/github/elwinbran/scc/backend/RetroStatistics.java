package com.github.elwinbran.scc.backend;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.github.elwinbran.scc.backend.interfaces.Statistic;
import com.github.elwinbran.scc.service.GameStatisticsViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple implementation for the player statistics that uses JSONBin for storage and
 * Retrofit to access it.
 *
 * @author Elwin Slokker
 */
public class RetroStatistics extends GameStatisticsViewModel
{

    private MutableLiveData<Statistic> liveStatistic = new MutableLiveData();

    private final StatisticApiService service = StatisticApiService.retrofit.create(StatisticApiService.class);

    /**
     * Primary constructor.
     *
     * @param key The key required to access the JSONbin repo.
     */
    public RetroStatistics(String key)
    {
        service.readStatistic(key).enqueue(new Callback<JSONStatistic>()
        {
            @Override
            public void onResponse(Call<JSONStatistic> call, Response<JSONStatistic> response)
            {
                liveStatistic.setValue(response.body());
            }

            @Override
            public void onFailure(Call<JSONStatistic> call, Throwable t)
            {
                throw new RuntimeException(t);
            }
        });
    }

    @Override
    public LiveData<Statistic> retrieve()
    {
        return liveStatistic;
    }

    @Override
    public void incrementWins()
    {
        //TODO: implement
    }

    @Override
    public void incrementLosses()
    {
        //TODO: implement
    }

    @Override
    public void reset()
    {
        //TODO: implement
    }
}
