Features
---------

- Authorize the user details provided by identity provider and authenticate based on the client key and id

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/nucleus-auth/{version}/authorize |
| Auth | Not Required |
| Request Body Type | JSON |
| Response Code | 200 Successful |

**Response body json fields**

| Key name        | Nullable | comment                            |
|---------------  |----------|------------------------------------|
| firstname       | No       |                                    |
| lastname        | Yes      |                                    |
| username        | Yes      |                                    |
| identity_id     | No       |                                    |
| grant_type      | No       |                                    |
| client_id       | No       |                                    |
| client_key      | No       |                                    |
| return_url      | No       |                                    |


**Authorization Token**

```
Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MDk6MDMgSVNUIDIwMTY6MTQ1MzIwMzU0MzYwMQ==
```
**Request Body**

```json
{
    "client_key": "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==",
    "grant_type": "google",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "return_url": "http://www.gooru.org",
    "user": {
        "firstname": "gooru",
        "lastname": "my",
        "identity_id": "sheeban@gooru.org"
    }
}
```

**Response Body**

```json
{
    "user_id": "93660b52-6fc6-4bf9-828d-3434ea87fd03",
    "username": "goorumy",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "provided_at": 1454148793345,
    "cdn_urls": {
        "profile.cdn": "//profile.gooru.org.com"
    },
    "prefs": {
        "standard_preference": {
            "K12.LA": "0dbd23e0-05ef-11e5-bfe7-22000abfab1d",
            "K12.SC": "c6869092-bfdb-11e3-a253-12313f070480"
        }
    },
    "access_token": "OTM2NjBiNTItNmZjNi00YmY5LTgyOGQtMzQzNGVhODdmZDAzOlNhdCBKYW4gMzAgMTU6NDM6MTMgSVNUIDIwMTY6MTQ1NDE0ODc5MzM0Ng=="
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json"  -d '{"client_key" : "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==", "grant_type" : "google", "client_id" : "ba956a97-ae15-11e5-a302-f8a963065976", "return_url" : "http://www.gooru.org", "user" : {"firstname" : "gooru", "lastname" : "my",  "identity_id" : "sheeban@gooru.org"}}' -X POST http://nucleus.gooru.org/api/nucleus-auth/v1/authorize
```
