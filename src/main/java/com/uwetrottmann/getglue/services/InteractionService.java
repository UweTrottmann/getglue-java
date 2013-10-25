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
import com.uwetrottmann.getglue.entities.GetGlueInteractionResource;

import java.util.List;

import retrofit.http.EncodedPath;
import retrofit.http.GET;

/**
 * Endpoints for <a href="http://developer.getglue.com/#interaction-resources">Interaction
 * Resources</a>.
 */
public interface InteractionService {

    /**
     * Retrieves the specified interaction. Returns an Interaction resource.
     */
    @GET("/{interaction-id}")
    GetGlueInteractionResource get(
            @EncodedPath("interaction-id") String interactionId
    );

    /**
     * Retrieves the collection of vote interactions attached to the specified parent interaction.
     * Returns an array of Interaction resources.
     */
    @GET("/{interaction-id}/votes")
    List<GetGlueInteraction> votes(
            @EncodedPath("interaction-id") String interactionId
    );

    /**
     * Retrieves the collection of reply interactions attached to the specified parent interaction.
     * Returns an array of Interaction resources.
     */
    @GET("/{interaction-id}/replies")
    List<GetGlueInteraction> replies(
            @EncodedPath("interaction-id") String interactionId
    );

}
