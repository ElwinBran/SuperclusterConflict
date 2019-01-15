package com.github.elwinbran.android.scc.app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * The manager for the card detail list.
 *
 * @author Elwin Slokker
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder>
{

    final private List<Pair<String, String>> details;

    public DetailAdapter(List<Pair<String, String>> details)
    {
        this.details = details;
    }


    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View detailItem = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_detail_item, viewGroup, false);
        return new DetailViewHolder(detailItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i)
    {
        detailViewHolder.replaceDisplay(this.details.get(i));
    }

    @Override
    public int getItemCount()
    {
        return this.details.size();
    }
}
