Change Log
==========

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