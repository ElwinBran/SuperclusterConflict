package com.github.elwinbran.android.scc.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.elwinbran.android.scc.app.CardDetailActivity;
import com.github.elwinbran.android.scc.app.R;

/**
 * The UI representation of a card in the demo.
 *
 * @author Elwin Slokker
 */
public class DemoCard extends Fragment
{
    private ImageView demoCardBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, parent, savedInstanceState);
        View fragment = inflater.inflate(R.layout.fragment_card, parent, false);
        Bundle args = getArguments();
        setContent(fragment, args);
        return fragment;
    }

    private void setContent(View view, Bundle data)
    {
        demoCardBack = view.findViewById(R.id.demo_card_back);
        final Intent viewDetail = new Intent(getActivity(), CardDetailActivity.class);
        Parcelable card = data.getParcelable(getActivity().getString(R.string.bundle_key));
        viewDetail.putExtra(getActivity().getString(R.string.bundle_key), card);
        demoCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(viewDetail);
            }
        });
    }
}
