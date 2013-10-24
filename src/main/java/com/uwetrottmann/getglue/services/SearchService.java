package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.entities.GetGlueObjects;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * NOT OFFICIALLY DOCUMENTED. CAN BREAK ANYTIME.
 */
public interface SearchService {

    @GET("/search/objects")
    GetGlueObjects search(
            @Query("q") String query
    );

}
