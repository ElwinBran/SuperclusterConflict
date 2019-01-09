package com.github.elwinbran.scc.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.github.elwinbran.android.scc.Card;
import com.github.elwinbran.android.scc.CardGroups;
import com.github.elwinbran.scc.backend.CardApiService;
import com.github.elwinbran.scc.backend.JsonBinGroup;
import com.github.elwinbran.scc.backend.RetroCardRepository;
import com.github.elwinbran.scc.backend.interfaces.CardRepository;
import com.github.elwinbran.scc.utility.SimpleCardGroups;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        CardApiService service = CardApiService.retrofit.create(CardApiService.class);
        service.readCards(key).enqueue(new Callback<JsonBinGroup>() {
            @Override
            public void onResponse(Call<JsonBinGroup> call, Response<JsonBinGroup> response)
            {
                CardGroups demo = new SimpleCardGroups(response.body().name(), response.body().cards());
                Log.d("none", demo.group("Demo").iterator().next().properties().entry("name").resultText());
            }

            @Override
            public void onFailure(Call<JsonBinGroup> call, Throwable t)
            {
                Log.d("none",t.toString());
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
}
