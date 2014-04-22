Change Log
==========

Version 1.3.1 *(2014-03-22)*
--------------------------------

 * Update to okhttp 1.5.4.
 * Update to retrofit 1.5.0.
 * Update to Apache Oltu OAuth 2.0 Client 1.0.0.

Version 1.3.0 *(2014-03-19)*
--------------------------------

 * Add `comment` field to `GetGlueInteraction`. This holds e.g. check-in comments, might be null.
 * Use okhttp 1.5.2.

Version 1.2.3 *(2014-03-09)*
--------------------------------

 * Use okhttp 1.5.0 which uses a private SSL context (so we don't have to set one on our own).

Version 1.2.2 *(2014-02-22)*
--------------------------------

 * Do not crash if there isn't an error response for HTTP 400 and 401 responses when getting the access token.
 * Use okhttp 1.3.0.
 * Use retrofit 1.4.1.

Version 1.2.1 *(2013-12-22)*
--------------------------------

 * Also use a private SSL context with retrofit (by setting a custom OkHttp client).

Version 1.2.0 *(2013-12-21)*
--------------------------------

 * Use a private SSL context for OkHttp. Fixes potential crashes in libssl with other libraries (e.g. Google Analytics) not expecting modifications of the global SSL context.
 * Update to retrofit 1.3.0.

Version 1.1.0 *(2013-10-25)*
--------------------------------

 * Added (unofficial) search endpoints.
 * Support all object and interaction endpoints, some renamed to be more uniform and simple.
 * Added `getAccessTokenResponse()` using a custom okhttp-based HttpClient (can follow protocol redirects).

Version 1.0.0 *(2013-10-22)*
--------------------------------

 * Initial release.