Features
---------

- This API is used to return access token of the user based on the user details present in the request. ```reference_id``` is mandatory to be passed. If user does not exists in the system, new user will be provisioned using the user details.

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/internal/{version}/sso |
| Authorization | Required |
| Request Body Type | JSON |
| Response Code | 200 Successful |

**Response body json fields**

| Key name        | Nullable | comment                            |
|-----------------|----------|------------------------------------|
| firstname       | No       | First name                         |
| lastname        | Yes      | Last name                          |
| username        | Yes      |                                    |
| email_id		  | Yes		 |									  |
| reference_id    | No       | User Id of the client user         |
| client_id       | No       | Client ID of the client            |
| client_key      | No       | Client Key of the client           |


**Authorization**

Basic authorization is required using Client Id and Client Key

```
Authorization: Basic YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2OmMyaGxaV0poYmtCbmIyOXlkV3hsWVhKdWFXNW5MbTl5Wnc9PQ==
```

**Request Body**

```json
{
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "client_key": "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==",
    "user": {
        "firstname": "gooru",
        "lastname": "my",
        "reference_id": "123456789",
        "email_id" : "user@gooru.org"
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
curl -i  -H "Content-Type: application/json"  -d '{"client_key" : "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==", "client_id" : "ba956a97-ae15-11e5-a302-f8a963065976", "user" : {"firstname" : "gooru", "lastname" : "my",  "reference_id" : "123456789", "email_id" : "user@gooru.org"}}' -X POST http://nucleus.gooru.org/api/nucleus-auth/v1/authorize
```
