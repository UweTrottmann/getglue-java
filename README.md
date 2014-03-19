getglue-java
============

A Java wrapper around the [v3 API of tvtag (formerly GetGlue)][1] using retrofit.

Usage
=====
Add the following dependency to your Gradle project:
```
compile 'com.uwetrottmann:getglue-java:1.3.0'
```

or your Maven project:
```
<dependency>
    <groupId>com.uwetrottmann</groupId>
    <artifactId>getglue-java</artifactId>
    <version>1.3.0</version>
</dependency>
```

Dependencies
------------

If you rather use the [released jar][3], add dependencies yourself as you see fit.
For example for Gradle:
```
compile 'com.squareup.okhttp:okhttp:1.5.2'
compile 'com.squareup.retrofit:retrofit:1.4.1'
compile 'org.apache.oltu.oauth2:org.apache.oltu.oauth2.client:0.31'
```

Or for Maven:
```
<dependency>
  <groupId>com.squareup.okhttp</groupId>
  <artifactId>okhttp</artifactId>
  <version>1.5.2</version>
</dependency>
<dependency>
  <groupId>com.squareup.retrofit</groupId>
  <artifactId>retrofit</artifactId>
  <version>1.4.1</version>
</dependency>
<dependency>
    <groupId>org.apache.oltu.oauth2</groupId>
    <artifactId>org.apache.oltu.oauth2.client</artifactId>
    <version>0.31</version>
</dependency>
```

Calling an endpoint
-------------------
Once you have an access token you can make API calls by using the respective service:
```
// You can keep these around
GetGlue getglue = new GetGlue();
getglue.setAccessToken(<access-token>);
ObjectService service = getglue.objectService();

// Call any of the available endpoints
service.checkin("tv_shows/glee", "This is going to be hilarious.");
```

Authorization via OAuth
-----------------------
GetGlue uses OAuth 2.0 to authenticate the apps that use their API.
First, register your app at the [GetGlue OAuth portal][2] to obtain an OAuth client id and client secret.
Before using the API the user has to authorize your app so you can get a valid access token.
```
OAuthClientRequest request = GetGlue.getAuthorizationRequest(OAUTH_CLIENT_ID, OAUTH_CALLBACK_URL);

// Load authUrl in a web browser, so the user can sign in and authorize your app.
// GetGlue will then redirect to OAUTH_CALLBACK_URL once finished.
String authUrl = request.getLocationUri();

// Intercept the OAUTH_CALLBACK_URL and extract the auth code from the query parameter,
// e.g. http://mycallbackurl.com&code=<auth code>
```

This auth code can be used once to exchange it for an OAuth access and refresh token.
```
OAuthAccessTokenResponse response = GetGlue.getAccessTokenResponse(
                        OAUTH_CLIENT_ID,
                        OAUTH_CLIENT_SECRET,
                        OAUTH_CALLBACK_URL,
                        <auth code>
                );

// store access and refresh token, as well as expiration date of access token
long expiresIn = response.getExpiresIn(); // in seconds
String accessToken = response.getAccessToken(); // use with GetGlue.setAccessToken()
String refreshToken = response.getRefreshToken();
```

Once the access token is expired, you can try getting a new one by sending the refresh token instead
of an auth code when using `GetGlue.getAccessTokenResponse()`.

This can fail, in which case you have to ask the user to authenticate again (see above).

License
=======

    Copyright 2013-2014 Uwe Trottmann

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



 [1]: http://developer.getglue.com
 [2]: https://api.getglue.com/oauth2/
 [3]: https://github.com/UweTrottmann/getglue-java/releases
