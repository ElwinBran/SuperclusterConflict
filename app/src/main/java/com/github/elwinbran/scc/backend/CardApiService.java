package com.github.elwinbran.scc.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * A retrofit implementation for JSONbin.io.
 * It links to a predefined 'standard collection of SCC' cards.
 *
 * @author Elwin Slokker
 */
public interface CardApiService
{
    final static String BASE_URL = "https://api.jsonbin.io/b/5c322bd105d34b26f2030c84/1";

    final static String KEY_KEY = "secret-key";

    /**
     * Create a retrofit client.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * Retrieves the list of all cards (a card group) in the standard collection of SCC cards.
     *
     * @param key The required key to gain access to reading.
     * @return The group full of cards.
     */
    @GET
    Call<SingleJSONCardGroup> readCards(@Header(KEY_KEY) String key);


    /**
     * Replaces the current card group in the standard collection of SCC cards.
     *
     * @param cardGroup The new card group to replace the old.
     * @param key The required key to gain access to the replacement.
     * @return The full HTTP response body.
     */
    @Headers({"content-type: application/json", "versioning: false"})
    @PUT
    Call<ResponseBody> updateCards(@Body SingleJSONCardGroup cardGroup, @Header(KEY_KEY) String key);
}
