package com.uwetrottmann.getglue;

import junit.framework.TestCase;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.io.IOException;

public abstract class BaseTestCase extends TestCase {

    protected static final String CLIENT_ID = "7FD930E5C9D030F696ACA631343EB3";
    protected static final String CLIENT_SECRET = "EB4B93F673B95A5A2460CF983BB0A4";
    private static final String TEMPORARY_ACCESS_TOKEN = "B341108A36B7499839648979D4739E";  /* Expires Feb. 20, 2014, 10:20 a.m. */
    protected static final String REDIRECT_URI = "http://localhost";
    private final GetGlue mManager = new GetGlue();

    @Override
    protected void setUp() throws OAuthSystemException, IOException, OAuthProblemException {
        getManager().setIsDebug(true);
        getManager().setAccessToken(TEMPORARY_ACCESS_TOKEN);
    }

    protected final GetGlue getManager() {
        return mManager;
    }
}
