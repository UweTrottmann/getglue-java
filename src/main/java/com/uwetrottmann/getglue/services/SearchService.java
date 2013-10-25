package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.entities.GetGlueObjects;
import com.uwetrottmann.getglue.enumerations.GetGlueObjectType;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * NOT OFFICIALLY DOCUMENTED. CAN BREAK ANYTIME.
 */
public interface SearchService {

    @GET("/search/objects")
    GetGlueObjects searchAnyObject(
            @Query("q") String query
    );

    @GET("/search/objects?category=" + GetGlueObjectType.TV_SHOWS)
    GetGlueObjects searchTvShows(
            @Query("q") String query
    );

    @GET("/search/objects?category=" + GetGlueObjectType.MOVIES)
    GetGlueObjects searchMovies(
            @Query("q") String query
    );

    @GET("/search/objects?category=" + GetGlueObjectType.SPORTS)
    GetGlueObjects searchSports(
            @Query("q") String query
    );

    @GET("/search/objects?category=" + GetGlueObjectType.GAMES)
    GetGlueObjects searchGames(
            @Query("q") String query
    );

}
