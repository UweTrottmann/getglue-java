/*
 * Copyright 2013 Uwe Trottmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uwetrottmann.getglue.services;

import com.uwetrottmann.getglue.entities.GetGlueInteraction;
import com.uwetrottmann.getglue.entities.GetGlueObjectResource;

import java.util.List;

import retrofit.http.EncodedPath;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Endpoints for <a href="http://developer.getglue.com/#object-resources">Object Resources</a>.
 */
public interface ObjectService {

    /**
     * Retrieve a GetGlue object. Can be any TV show, movie, sports team, game, or episode.
     */
    @GET("/{object-id}")
    GetGlueObjectResource get(
            @EncodedPath("object-id") String objectId
    );

    /**
     * Creates a new check-in to the object with the authenticated user. Returns an Interaction
     * resource containing at least an id.
     */
    @POST("/{object-id}/checkins")
    GetGlueInteraction checkin(
            @EncodedPath("object-id") String objectId
    );

    /**
     * Creates a new check-in to the object with the authenticated user. Returns an Interaction
     * resource containing at least an id.
     */
    @FormUrlEncoded
    @POST("/{object-id}/checkins")
    GetGlueInteraction checkin(
            @EncodedPath("object-id") String objectId,
            @Field("comment") String comment
    );

    /**
     * Retrieve a pageable collection of all feed interactions around the specified object. A feed
     * is a collection of curated interactions selected from the set of all interactions. Returns an
     * array of Interaction resources.
     */
    @GET("/{object-id}/feed")
    List<GetGlueInteraction> feed(
            @EncodedPath("object-id") String objectId
    );

    /**
     * Creates a new like for the object with the authenticated user. Returns an Interaction object
     * containing at least an id.
     */
    @POST("/{object-id}/likes")
    GetGlueInteraction like(
            @EncodedPath("object-id") String objectId
    );

}
