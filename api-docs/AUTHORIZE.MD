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
| Response Code | 302 Found |

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
{"client_key" : "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==", "grant_type" : "google", "client_id" : "ba956a97-ae15-11e5-a302-f8a963065976", "return_url" : "http://www.gooru.org", "user" : {"firstname" : "gooru", "lastname" : "my",  "identity_id" : "sheeban@gooru.org"}}
```

**Location Header**

```
Location: http://www.gooru.org?access_token=MzA4ZjQ4NjUtNjIwYS00MjVmLTllMTgtYmJkODczMTdiMDI5OkZyaSBKYW4gMjIgMTU6NDY6MDYgSVNUIDIwMTY6MTQ1MzQ1Nzc2NjA0Nw==
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MDk6MDMgSVNUIDIwMTY6MTQ1MzIwMzU0MzYwMQ==" -d '{"client_key" : "c2hlZWJhbkBnb29ydWxlYXJuaW5nLm9yZw==", "grant_type" : "google", "client_id" : "ba956a97-ae15-11e5-a302-f8a963065976", "return_url" : "http://www.gooru.org", "user" : {"firstname" : "gooru", "lastname" : "my",  "identity_id" : "sheeban@gooru.org"}}' -X POST http://127.0.0.1:8080/api/nucleus-auth/v1/authorize
```
