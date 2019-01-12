package com.github.elwinbran.android.scc.backend;

import com.github.elwinbran.android.scc.CardGroups;
import com.github.elwinbran.android.scc.api.CardRepository;
import com.github.elwinbran.android.scc.utility.SimpleCardGroups;

import java.io.IOException;

/**
 * This implementation provides access to the JSONbin through Retrofit 2.
 *
 * @author Elwin Slokker
 */
public class RetroCardRepository implements CardRepository
{
    private final String key;

    private final CardApiService service;

    public RetroCardRepository(String key)
    {
        service = CardApiService.retrofit.create(CardApiService.class);
        this.key = key;
    }

    @Override
    public CardGroups cardGroups()
    {
        try
        {
            JsonBinGroup temp = service.readCards(key).execute().body();
            return new SimpleCardGroups(temp.name(), temp.cards());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
