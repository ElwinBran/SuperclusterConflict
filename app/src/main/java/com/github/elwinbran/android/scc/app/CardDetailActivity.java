package com.github.elwinbran.android.scc.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * This activity allows one to see the details of a card.
 *
 * @author Elwin Slokker
 */
public class CardDetailActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        setContentView(R.layout.popup_card_detail);

        DisplayMetrics dm  = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        Integer width = dm.widthPixels;
        Integer height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6d), (int)(height * 0.2));
    }
}
