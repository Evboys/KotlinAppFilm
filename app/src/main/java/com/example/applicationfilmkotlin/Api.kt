package com.example.applicationfilmkotlin

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key") apikey: String): TmdbMoviesResult

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apikey: String,
        @Query("query") motcle: String
    ): TmdbMoviesResult

    @GET("movie/{id}")
    suspend fun detailMovie(
        @Path("id") id: Int,
        @Query("api_key") apikey: String,
        @Query("append_to_response") credits: String,
        @Query("language") language:String
    ): DetailMovie

    @GET("trending/tv/week")
    suspend fun lastSeries(@Query("api_key") apikey: String): TmdbSeriesResult

    @GET("search/tv")
    suspend fun searchSeries(
        @Query("api_key") apikey: String,
        @Query("query") motcle: String
    ): TmdbSeriesResult

    @GET("tv/{id}")
    suspend fun detailSerie(
        @Path("id") id: Int,
        @Query("api_key") apikey: String,
        @Query("append_to_response") credits: String,
        @Query("language") language:String
    ): DetailSerie

    @GET("trending/person/week")
    suspend fun lastActors(@Query("api_key") apikey: String): TmdbActorsResult

    @GET("search/person")
    suspend fun searchActors(
        @Query("api_key") apikey: String,
        @Query("query") motcle: String
    ): TmdbActorsResult

    @GET("person/{id}")
    suspend fun detailActor(
        @Path("id") id: Int,
        @Query("api_key") apikey: String,
        @Query("language") language:String
    ): DetailActor
}