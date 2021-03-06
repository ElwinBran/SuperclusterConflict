package com.github.elwinbran.android.scc.backend;

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
 * Retrofit implementation to retrieve Statistics from JSONbin.
 *
 * @author Elwin Slokker
 */
public interface StatisticApiService
{
    final static String BASE_URL = "https://api.jsonbin.io/b/5c3778b005d34b26f2068eff/";

    /**
     * Create a retrofit client.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    /**
     * Retrieves the players current statistic.
     *
     * @param key The required key to gain access to reading.
     * @return An object that contains the statistics of the player like wins and losses.
     */
    @GET(".")
    Call<JSONStatistic> readStatistic(@Header(CardApiService.KEY_KEY) String key);

    @Headers({"content-type: application/json", "versioning: false"})
    @PUT(".")
    Call<ResponseBody> updateStatistics(@Body JSONStatistic statistic,
                                        @Header(CardApiService.KEY_KEY) String key);
}
