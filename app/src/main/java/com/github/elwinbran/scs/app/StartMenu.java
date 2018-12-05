package com.github.elwinbran.scs.app;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.elwinbran.scs.com.githhub.elwinbran.scs.support.Executor;

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
