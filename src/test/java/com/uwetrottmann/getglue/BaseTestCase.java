package com.uwetrottmann.getglue;

import junit.framework.TestCase;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class BaseTestCase extends TestCase {

    private static final String CLIENT_ID = "7FD930E5C9D030F696ACA631343EB3";
    private static final String CLIENT_SECRET = "EB4B93F673B95A5A2460CF983BB0A4";
    private static final String TEMPORARY_ACCESS_TOKEN = "FA1429A2D4B3FA39EF57E15689A6B4";  /* Expires Dec. 21, 2013, 8:35 a.m. */
    private static final String REDIRECT_URI = "http://localhost";
    private final ServiceManager mManager = new ServiceManager();

    @Override
    protected void setUp() throws OAuthSystemException, IOException, OAuthProblemException {
        getManager().setIsDebug(true);

        OAuthClientRequest request = getManager().getAuthorizationRequest(CLIENT_ID, REDIRECT_URI);
        System.out.println("Please visit: " + request.getLocationUri());

//        System.out.print("Now enter the OAuth code you have received in redirect uri ");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String code = br.readLine();
//
//        request = getManager().getAccessTokenRequest(CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, code);
//
//        OAuthClient client = new OAuthClient(new URLConnectionClient());
//        OAuthJSONAccessTokenResponse tokenResponse = client.accessToken(request);
//        System.out.println(
//                "Access Token: " + tokenResponse.getAccessToken() + ", Expires in: " + tokenResponse
//                        .getExpiresIn());

        getManager().setAccessToken(TEMPORARY_ACCESS_TOKEN);
    }

    protected final ServiceManager getManager() {
        return mManager;
    }
}
