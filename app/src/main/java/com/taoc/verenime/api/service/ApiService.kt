package com.taoc.verenime.api.service

import com.taoc.verenime.api.responses.AnimeListResponse
import com.taoc.verenime.api.responses.DetailAnimeResponse
import com.taoc.verenime.api.responses.StreamingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("ongoing")
    suspend fun getOngoingAnime(@Query("page") page: Int = 1): AnimeListResponse

    @GET("completed")
    suspend fun getCompletedAnime(@Query("page") page: Int = 1): AnimeListResponse

    @GET("anime")
    suspend fun getAllAnime(@Query("page") page: Int = 1): AnimeListResponse

    @GET("search")
    suspend fun searchAnime(@Query("q") query: String): AnimeListResponse

    @GET("anime/{animeId}")
    suspend fun getAnimeDetail(@Path("animeId") animeId: String): DetailAnimeResponse

    @GET("genres/{genreId}")
    suspend fun getAnimeByGenre(
        @Path("genreId") genreId: String,
        @Query("page") page: Int = 1
    ): AnimeListResponse

    @GET("episode/{episodeId}")
    suspend fun getStreamingData(@Path("episodeId") episodeId: String): StreamingResponse

    @GET("server/{serverId}")
    suspend fun getServerData(@Path("serverId") serverId: String): StreamingResponse

    companion object {
        private const val BASE_URL = "http://192.168.1.100:3001/otakudesu/"

        fun create(): ApiService {
            val logging = okhttp3.logging.HttpLoggingInterceptor().apply {
                level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            }
            val client = okhttp3.OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }
}