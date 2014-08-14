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
package com.uwetrottmann.getglue;

import com.uwetrottmann.getglue.client.GetGlueHttpClient;
import com.uwetrottmann.getglue.services.InteractionService;
import com.uwetrottmann.getglue.services.ObjectService;
import com.uwetrottmann.getglue.services.SearchService;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class GetGlue {

    public static final String API_URL = "https://api.getglue.com/v3";

    public static final String API_V4_URL = "http://api.getglue.com/v4";

    public static final String OAUTH2_AUTHORIZATION_URL
            = "https://api.getglue.com/oauth2/authorize";

    public static final String OAUTH2_ACCESS_TOKEN_URL
            = "https://api.getglue.com/oauth2/access_token";

    private boolean isDebug;
    private String accessToken;
    private RestAdapter restAdapter;
    private RestAdapter restAdapterApiFour;

    /**
     * Create a new manager instance. Your next call should probably be {@link #setAccessToken(String)}.
     */
    public GetGlue() {
    }

    /**
     * Build an OAuth authorization request.
     *
     * @param clientId The OAuth client id obtained from tvtag.
     * @param redirectUri The URI to redirect to with appended auth code query parameter.
     * @throws OAuthSystemException
     */
    public static OAuthClientRequest getAuthorizationRequest(String clientId, String redirectUri)
            throws OAuthSystemException {
        return OAuthClientRequest
                .authorizationLocation(OAUTH2_AUTHORIZATION_URL)
                .setScope("public read write")
                .setResponseType(ResponseType.CODE.toString())
                .setClientId(clientId)
                .setRedirectURI(redirectUri)
                .buildQueryMessage();
    }

    /**
     * Build an OAuth access token request.
     *
     * @param clientId The OAuth client id obtained from tvtag.
     * @param clientSecret The OAuth client secret obtained from tvtag.
     * @param redirectUri The redirect URI previously used for obtaining the auth code.
     * @param authCode A previously obtained auth code.
     * @return A tvtag OAuth access token request.
     * @throws OAuthSystemException
     */
    public static OAuthClientRequest getAccessTokenRequest(String clientId, String clientSecret, String redirectUri,
            String authCode) throws OAuthSystemException {
        return OAuthClientRequest
                .tokenLocation(OAUTH2_ACCESS_TOKEN_URL)
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectURI(redirectUri)
                .setCode(authCode)
                .buildQueryMessage();
    }

    /**
     * Request an access token from tvtag. Builds the request with {@link #getAccessTokenRequest(String, String, String,
     * String)} and executes it, then returns the response which includes the access and refresh tokens.
     *
     * @param clientId The OAuth client id obtained from tvtag.
     * @param clientSecret The OAuth client secret obtained from tvtag.
     * @param redirectUri The redirect URI previously used for obtaining the auth code.
     * @param authCode A previously obtained auth code.
     * @return A new OAuth access and refresh token from tvtag.
     * @throws OAuthSystemException
     * @throws OAuthProblemException
     */
    public static OAuthAccessTokenResponse getAccessTokenResponse(String clientId,
            String clientSecret, String redirectUri, String authCode)
            throws OAuthSystemException, OAuthProblemException {
        OAuthClientRequest request = getAccessTokenRequest(clientId, clientSecret, redirectUri, authCode);

        // create HTTP client which is able to follow protocol redirects (tvtag likes to redirect from HTTPS to HTTP)
        OAuthClient client = new OAuthClient(new GetGlueHttpClient());
        return client.accessToken(request);
    }

    /**
     * Set the {@link retrofit.RestAdapter} log levels.
     *
     * @param isDebug If true, the log level is set to {@link retrofit.RestAdapter.LogLevel#FULL}. Otherwise {@link
     * retrofit.RestAdapter.LogLevel#NONE}.
     */
    public GetGlue setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;

        RestAdapter.LogLevel logLevel = isDebug ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE;
        if (restAdapter != null) {
            restAdapter.setLogLevel(logLevel);
        }
        if (restAdapterApiFour != null) {
            restAdapterApiFour.setLogLevel(logLevel);
        }

        return this;
    }

    /**
     * Set a valid OAuth access token for tvtag. Make sure to get new service instances by calling any of the service
     * methods afterwards.
     *
     * @param token A valid OAuth access token.
     */
    public void setAccessToken(String token) {
        accessToken = token;
        restAdapter = null;
    }

    /**
     * Create a new {@link retrofit.RestAdapter.Builder}. Override this to e.g. set your own client or executor.
     *
     * @return A {@link retrofit.RestAdapter.Builder} with no modifications.
     */
    protected RestAdapter.Builder newRestAdapterBuilder() {
        return new RestAdapter.Builder();
    }

    /**
     * Return the current {@link retrofit.RestAdapter} instance. If none exists (first call, access token changed),
     * builds a new one.
     *
     * <p> When building, sets the endpoint, a {@link retrofit.RequestInterceptor} which adds the access token as query
     * param and the log level.
     */
    protected RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            RestAdapter.Builder builder = newRestAdapterBuilder();
            builder.setEndpoint(API_URL);

            // Supply OAuth 2.0 access token
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addQueryParam("access_token", accessToken);
                }
            });

            if (isDebug) {
                builder.setLogLevel(RestAdapter.LogLevel.FULL);
            }

            restAdapter = builder.build();
        }

        return restAdapter;
    }

    /**
     * Return the current {@link retrofit.RestAdapter} instance for v4 API calls (currently search only). If none exists
     * (first call), builds a new one.
     *
     * <p> When building, sets the endpoint and log level.
     */
    protected RestAdapter getRestAdapterApiFour() {
        if (restAdapterApiFour == null) {
            RestAdapter.Builder builder = newRestAdapterBuilder();
            builder.setEndpoint(API_V4_URL);

            if (isDebug) {
                builder.setLogLevel(RestAdapter.LogLevel.FULL);
            }

            restAdapterApiFour = builder.build();
        }

        return restAdapterApiFour;
    }

    public ObjectService objectService() {
        return getRestAdapter().create(ObjectService.class);
    }

    public InteractionService interactionService() {
        return getRestAdapter().create(InteractionService.class);
    }

    public SearchService searchService() {
        return getRestAdapterApiFour().create(SearchService.class);
    }
}
