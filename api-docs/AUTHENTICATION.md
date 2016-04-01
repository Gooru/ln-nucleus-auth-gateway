Features
----------
- Generate anonymous user access Token.
- Generate authenticate user access token.
- Get access token.
- Delete access token.


###Generate anonymous user access Token

   The anonymous authentication flow can be used to authenticate when the consumer already has the client id and client secret.
   This authentication flow involves passing the client id and secret. Use this authentication flow only when necessary. No refresh token will be issued.

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/nucleus-auth/{version}/token |
| Auth | client key and id is needed |
| Request Body Type | JSON |
| Response Code | 200 Successful |

**Request Body**

```javascript
{
   "client_key": "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==",
   "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
   "grant_type": "anonymous"
}
```

**Response Body**

```json
{
    "user_id": "anonymous",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "provided_at": 1453472200140,
    "cdn_urls": {
        "profile.cdn": "//profile.gooru.org.com"
    },
    "prefs": {
        "standard_preference": {
             "K12.LA": "0dbd23e0-05ef-11e5-bfe7-22000abfab1d",
             "K12.SC": "c6869092-bfdb-11e3-a253-12313f070480"
         }
    },
    "access_token": "YW5vbnltb3VzOkZyaSBKYW4gMjIgMTk6NDY6NDAgSVNUIDIwMTY6MTQ1MzQ3MjIwMDE0MA=="
}
```

**CURL snippet**

```json
curl -i  -H "Content-Type: application/json"  -d '{
    "client_key": "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "grant_type": "anonymous"
}' -X POST http://nucleus.gooru.org/api/nucleus-auth/v1/token

```

###Username and password authentication flow

   The username-password authentication flow can be used to authenticate when the consumer already has the user’s credentials.

   This authentication flow involves passing the user's credentials back and forth. Use this authentication flow only when necessary. No refresh token will be issued.


**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/nucleus-auth/{version}/token |
| Auth | client key and id is needed |
| Request Body Type | JSON |
| Authorization Header | Build a string of the form username:password and Base64 encode the string |
| Response Code | 200 Successful |

**Authorization Header**

- Build a string of the form username:password
- Base64 encode the string
- Supply an "Authorization" header with content "Basic " followed by the encoded string. For example, the string "gooru:mygooru123" encodes to "Z29vcnU6bXlnb29ydTEyMw==" in base64

```Authorization: Basic Z29vcnU6bXlnb29ydTEyMw==```

**Request Body**

```json
{
    "client_key": "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "grant_type": "credential"
}
```

**Response Body**

```json
{
    "user_id": "d349be93-16e6-4e0f-a762-8a287c6aed6b",
    "username": "goorudon",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "provided_at": 1459529618410,
    "prefs": {
        "standard_preference": {
            "K12.LA": "CCSS",
            "K12.SC": "CCSS"
        },
        "email_id": "mygooru123@gmail.com"
    },
    "cdn_urls": {
        "user_cdn_url": "//dev-user-gooru-org.s3-us-west-1.amazonaws.com/",
        "content_cdn_url": "//dev-content-gooru-org.s3-us-west-1.amazonaws.com/"
    },
    "access_token": "MTQ1OTUyOTYxODQxMDpkMzQ5YmU5My0xNmU2LTRlMGYtYTc2Mi04YTI4N2M2YWVkNmI6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2",
    "firstname": "gooru",
    "lastname": "learning"
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Basic b2liZ2FuZzlAZ29vcnUub3JnOmRvbjEyMzQ=" -d '{
    "client_key": "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "grant_type": "credential"
}' -X POST http://nucleus.gooru.org/api/nucleus-auth/v1/token
```

##Get access token  details 

 This API response has the basic details about the user and  auth client.

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus-auth/{version}/token |
| Auth | Required |
| Request Body Type | None |
| Authorization Header | Token [access_token] |
| Response Code | 200 Successful |

**Authorization Header**

```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OldlZCBKYW4gMjAgMTE6NDM6MzEgSVNUIDIwMTY6MTQ1MzI3MDQxMTkxNA==
```

**Response Body**

```json 
{
    "user_id": "aada6abc-eb11-4550-981f-3a36a53b4907",
    "username": "rnfu34p13",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "provided_at": 1453366247336,
    "cdn_urls": {
        "profile.cdn": "//profile.gooru.org.com"
    }
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTQ6MjA6NDcgSVNUIDIwMTY6MTQ1MzM2NjI0NzMzNg==" -X GET http://nucleus.gooru.org/api/nucleus-auth/v1/token
```

##Delete access token 

It will revoke the access given to an application and make it invalid, it will be used for logout functionality.

| Name | Summary |
|------------------------|--------|
| HTTP Method | DELETE |
| End Point | /api/nucleus-auth/{version}/token |
| Auth | Required |
| Request Body Type | None |
| Authorization Header | Token [access_token] |
| Response Code | 204 No content |


**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTQ6MjA6NDcgSVNUIDIwMTY6MTQ1MzM2NjI0NzMzNg=="  -X DELETE http://nucleus.gooru.org/api/nucleus-auth/v1/token
```

