package com.github.elwinbran.android.scc.app;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Pair;

import com.github.elwinbran.android.scc.backend.ROOMCard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        ROOMCard pojoCard = getIntent().getParcelableExtra(getString(R.string.bundle_key));
        List<Pair<String, String>> details = new LinkedList<>();
        for(String key : pojoCard.getValues().keySet())
        {
            details.add(new Pair<String, String>(key, pojoCard.getValues().get(key)));
        }
        RecyclerView detailList = findViewById(R.id.card_detail_view);
        detailList.setAdapter(new DetailAdapter(details));
        detailList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DisplayMetrics dm  = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        Integer width = dm.widthPixels;
        Integer height = dm.heightPixels;
        Float fraction = getResources().getFraction(R.fraction.card_detail_width, 1, 1);
        getWindow().setLayout((int)(width * fraction), height);
    }
}
