package com.uwetrottmann.getglue;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import static org.fest.assertions.api.Assertions.assertThat;

public class GetGlueTest extends BaseTestCase {

    public void test_getAuthorizationRequest() throws OAuthSystemException {
        OAuthClientRequest request = GetGlue.getAuthorizationRequest(CLIENT_ID, REDIRECT_URI);
        System.out.println("GetGlue Authorization URL: " + request.getLocationUri());
        assertThat(request.getLocationUri())
                .isEqualTo("https://api.getglue.com/oauth2/authorize"
                        + "?scope=public+read+write"
                        + "&response_type=code"
                        + "&redirect_uri=http%3A%2F%2Flocalhost"
                        + "&client_id=" + CLIENT_ID);
    }

    public void test_getAccessTokenRequest() throws OAuthSystemException {
        String code = "S0meRand0mS1uff";
        OAuthClientRequest request = GetGlue
                .getAccessTokenRequest(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, code);
        System.out.println("GetGlue Access Token URL: " + request.getLocationUri());
        assertThat(request.getLocationUri())
                .isEqualTo("https://api.getglue.com/oauth2/access_token"
                        + "?code=" + code
                        + "&grant_type=authorization_code"
                        + "&client_secret=" + CLIENT_SECRET
                        + "&redirect_uri=http%3A%2F%2Flocalhost"
                        + "&client_id=" + CLIENT_ID);
    }

}
