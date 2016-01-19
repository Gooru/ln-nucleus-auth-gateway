Features
----------
- Anonymous user access Token.
- Authenticate user access token.

###Anonymous user access Token

   The anonymous authentication flow can be used to authenticate when the consumer already has the client id and client secret.
   This authentication flow involves passing the client id and secret. Use this authentication flow only when necessary. No refresh token will be issued.

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /nucleus-auth/{version}/token |
| Auth | Not Required |
| Request Body Type | JSON |
| Response Code | 200 Successful |

**Request Body**

```json
{"client_key" : "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==", "client_id" : "ba956a97-ae15-11e5-a302-f8a963065976", "grant_type" : "anonymous"}
```

**Response Body**

```json
{"user_id":"anonymous","client_id":"ba956a97-ae15-11e5-a302-f8a963065976","provided_at":1453203543600,"access_token":"YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MDk6MDMgSVNUIDIwMTY6MTQ1MzIwMzU0MzYwMQ==","cdn_urls":{"profile.cdn":"//profile.gooru.org.com"}}
```

**CURL snippet**

```json
curl -i  -H "Content-Type: application/json"  -d6a97-ae15-11e5-a302-f8a963065976", "grant_type" : "anonymous"}' -X POST http://127.0.0.1:8080/nucleus-auth/v1/token
```

###Username and password authentication flow

   The username-password authentication flow can be used to authenticate when the consumer already has the user’s credentials.

   This authentication flow involves passing the user's credentials back and forth. Use this authentication flow only when necessary. No refresh token will be issued.


**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /nucleus-auth/{version}/token |
| Auth | Not Required |
| Request Body Type | JSON |
| Authorization Header | Build a string of the form username:password and Base64 encode the string |
| Response Code | 200 Successful |

**Authorization Header**

- Build a string of the form username:password
- Base64 encode the string
- Supply an "Authorization" header with content "Basic " followed by the encoded string. For example, the string "gooru:mygooru123" encodes to "Z29vcnU6bXlnb29ydTEyMw==" in base64


**Request Body**

```json
{"client_key" : "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==", "client_id" : "ba956a97-ae15-11e5-a302-f8a963065976", "grant_type" : "credential"}
```

**Response Body**

```json
{"user_id":"aada6abc-eb11-4550-981f-3a36a53b4907","username":"rnfu34p13","client_id":"ba956a97-ae15-11e5-a302-f8a963065976","provided_at":1453200304366,"access_token":"YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlR1ZSBKYW4gMTkgMTY6MTU6MDQgSVNUIDIwMTY6MTQ1MzIwMDMwNDM2Nw==","cdn_urls":{"profile.cdn":"//profile.gooru.org.com"}}
```

**CURL snippet**

```json
curl -i  -H "Content-Type: application/json" -H "Authorization: Basic b2liZ2FuZzlAZ29vcnUub3JnOmRvbjEyMzQ=" -d '{"client_key" : "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==", "client_id" : "ba956a97-ae15-11e5-a302-f8a963065976", "grant_type" : "credential"}' -X POST http://127.0.0.1:8080/nucleus-auth/v1/token
```
