package com.github.elwinbran.scc.app;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.github.elwinbran.scc.backend.JSONStatistic;
import com.github.elwinbran.scc.backend.RetroStatistics;
import com.github.elwinbran.scc.backend.StatisticApiService;
import com.github.elwinbran.scc.backend.interfaces.Statistic;
import com.github.elwinbran.scc.service.GameStatisticsViewModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The entry point of the app code and start screen of the game.
 */
public class StartMenu extends FullscreenCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_start_menu);
        super.assign(findViewById(R.id.fullscreen_content));
        super.onCreate(savedInstanceState);
        Intent regularGame = new Intent(this, NormalGame.class);
        String key = BuildConfig.ApiKey;
        GameStatisticsViewModel staticicsSerivce = new RetroStatistics(key);
        staticicsSerivce.retrieve().observe(this, new Observer<Statistic>() {
            @Override
            public void onChanged(@Nullable Statistic statistic)
            {
                Toast.makeText(StartMenu.this, statisticText(statistic), Toast.LENGTH_SHORT);
            }
        });
        Button playGameButton = findViewById(R.id.playButton);
        playGameButton.setOnClickListener(new ExecutorListener(
                new IntentStarter(this, regularGame)));
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI
        //CardRepository repo = new RetroCardRepository(key);
        //repo.cardGroups().group("Demo");
    }

    private String statisticText(Statistic current)
    {
        String message = getString(R.string.wins_declaration);
        message = message.concat(current.wins().toString());
        message = message.concat(getString(R.string.losses_declaration));
        message = message.concat(current.losses().toString());
        return message;
    }
}
