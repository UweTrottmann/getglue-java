package com.uwetrottmann.getglue;

import com.uwetrottmann.getglue.services.ObjectService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class ServiceManager {

    private static final String API_URL = "https://api.getglue.com/v3";
    private boolean mIsDebug;

    public ServiceManager() {
    }

    public ServiceManager setIsDebug(boolean isDebug) {
        mIsDebug = isDebug;
        return this;
    }

    private RestAdapter buildRestAdapter() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setServer(API_URL);

        // TODO OAuth integration

        if (mIsDebug) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
        }

        return builder.build();
    }

    public ObjectService objectService() {
        ObjectService service = buildRestAdapter().create(ObjectService.class);
        return service;
    }
}
