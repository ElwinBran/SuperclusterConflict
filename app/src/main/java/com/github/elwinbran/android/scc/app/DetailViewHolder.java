package com.github.elwinbran.android.scc.app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

/**
 * The view holder for the detail overview list of cards in the demo.
 *
 * @author Elwin Slokker
 */
public class DetailViewHolder extends RecyclerView.ViewHolder
{
    final private TextView detailNameDisplay;

    final private TextView detailContentDisplay;

    public DetailViewHolder(@NonNull View itemView)
    {
        super(itemView);
        detailNameDisplay = itemView.findViewById(R.id.property_text_view);
        detailContentDisplay = itemView.findViewById(R.id.value_text_view);
    }

    public void replaceDisplay(Pair<String, String> newContent)
    {
        detailNameDisplay.setText(newContent.first);
        detailContentDisplay.setText(newContent.second);
    }
}
