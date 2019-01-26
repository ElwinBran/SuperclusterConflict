package com.github.elwinbran.android.scc.backend;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.github.elwinbran.android.scc.api.Statistic;
import com.github.elwinbran.android.scc.api.GameStatisticsViewModel;

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

    private final String key;

    /**
     * Primary constructor.
     *
     * @param key The key required to access the JSONbin repo.
     */
    public RetroStatistics(String key)
    {
        this.key = key;
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
        service.readStatistic(this.key).enqueue(new Callback<JSONStatistic>() {
            @Override
            public void onResponse(Call<JSONStatistic> call, Response<JSONStatistic> response)
            {
                JSONStatistic old = response.body();
                JSONStatistic newS = new JSONStatistic(((int)(long)old.wins() + 1), (int)(long)old.losses());
                service.updateStatistics(newS, RetroStatistics.this.key);
            }

            @Override
            public void onFailure(Call<JSONStatistic> call, Throwable t)
            {

            }
        });


    }

    @Override
    public void incrementLosses()
    {
        Statistic old = liveStatistic.getValue();
        service.updateStatistics(
                new JSONStatistic(((int)(long)old.wins()), (int)(long)old.losses() + 1),
                this.key);
    }

    @Override
    public void reset()
    {
        service.updateStatistics(new JSONStatistic(0,0), this.key);
    }
}
