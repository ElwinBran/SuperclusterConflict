package com.github.elwinbran.android.scc.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Collections;

/**
 * This activity allows one to see the details of a card.
 *
 * @author Elwin Slokker
 */
public class CardDetailActivity extends FullscreenCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstances)
    {
        setContentView(R.layout.popup_card_detail);
        super.assign(findViewById(R.id.detail_popup_root_layout));
        super.onCreate(savedInstances);


        RecyclerView detailList = findViewById(R.id.card_detail_view);
        detailList.setAdapter(new DetailAdapter(Collections.EMPTY_LIST));
        detailList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DisplayMetrics dm  = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        Integer width = dm.widthPixels;
        Integer height = dm.heightPixels;
        Float fraction = getResources().getFraction(R.fraction.card_detail_width, 1, 1);
        Log.d("none", fraction.toString());
        getWindow().setLayout((int)(width * fraction), height);
    }
}
