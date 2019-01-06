package com.github.elwinbran.scc.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.github.elwinbran.scc.app.R;

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

        Button playGameButton = findViewById(R.id.playButton);
        playGameButton.setOnClickListener(new ExecutorListener(
                new IntentStarter(this, regularGame)));
        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI
    }
}
