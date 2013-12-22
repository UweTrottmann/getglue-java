/*
 * Copyright 2013 Uwe Trottmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uwetrottmann.getglue;

import com.squareup.okhttp.OkHttpClient;

import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

public class Utils {

    /**
     * Create an OkHttpClient with its own private SSL context. Avoids libssl crash because other
     * libraries do not expect the global SSL context to be changed. Also see
     * https://github.com/square/okhttp/issues/184.
     */
    public static OkHttpClient createOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();

        // set private SSL context
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }
        okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());

        // set timeouts
        okHttpClient.setConnectTimeout(15 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(20 * 1000, TimeUnit.MILLISECONDS);

        return okHttpClient;
    }
}
