package com.github.elwinbran.android.scc.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Lets the user know the demo is won and completed.
 *
 * @author Elwin Slokker
 */
public class DemoWonActivity extends FullscreenCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstance)
    {
        setContentView(R.layout.activity_demo_won);
        super.assign(findViewById(R.id.win_root));
        super.onCreate(savedInstance);
        Button confirmButton = findViewById(R.id.button_confirm_win);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(DemoWonActivity.this, StartMenu.class));
            }
        });
    }
}
