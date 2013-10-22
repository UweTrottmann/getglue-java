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

    private static final String CLIENT_ID = "8D195792E4A18575EE0D50CE42A11F";
    private static final String CLIENT_SECRET = "68BA81EC69ED72D8DF0D3C705EC40B";
    private static final String TEMPORARY_ACCESS_TOKEN = "!-- FILL IN ACCESS TOKEN --!";
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
