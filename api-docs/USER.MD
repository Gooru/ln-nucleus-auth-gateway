Features
----------
- Create user account
- Update user account 
- Fetch user details
- Find user by username or email Id
- Forgot password
- Reset un-authenticate user password
- Reset authenticate user password.
- Change email address
- Confirm email address
- Resend confirmation email
- Find users by id



###Create user account

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/nucleus-auth/{version}/users |
| Auth | Required |
| Authorization Header | Authorization: Token [anonymous access token]
| Request Body Type | JSON |
| Response Code | 201 Created |

**Response body json fields**

| Key name      | Nullable | comment                            |
|---------------|----------|------------------------------------|
| firstname     | No       |                                    |
| lastname      | No       |                                    |
| username      | No       | character must be between 4 and 20 |
| email_id      | No       |                                    |
| password      | No       | character must be between 5 and 14 |
| user_category | No       |                                    |
| grade         | Yes      |                                    |
| gender        | Yes      |                                    |


**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```
**Request Body**

```json
{
    "firstname": "gooru",
    "lastname": "learning",
    "username": "gooru",
    "email_id": "mygooru@gmail.com",
    "password": "mygooru123",
    "birth_date": "11/12/1987",
    "user_category": "teacher",
    "gender": "male",
    "grade": ["5", "9"]
}
```

**Location Header**

```
Location: /users/95f0da69-fb0e-401a-8c9c-37acdba55ff0
```

**Response Body**

```json
{
    "user_id": "95f0da69-fb0e-401a-8c9c-37acdba55ff0",
    "username": "gooru",
    "client_id": "ba956a97-ae15-11e5-a302-f8a963065976",
    "provided_at": 1453377491783,
    "cdn_urls": {
        "profile.cdn": "//profile.gooru.org.com"
    },
    "access_token": "OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==" -d '{
    "firstname": "gooru",
    "lastname": "learning",
    "username": "gooru",
    "email_id": "mygooru@gmail.com",
    "password": "mygooru123",
    "birth_date": "11/12/1987",
    "user_category": "teacher",
    "gender": "male",
    "grade": [
        "5",
        "9"
    ]
}' -X POST http://nucleus.gooru.org/api/nucleus-auth/v1/users
```


##Update user account 

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /api/nucleus-auth/{version}/users/me |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticated access token]
| Request Body Type | JSON |
| Response Code | 204 No output |

**Response body json fields**

| Field name          | Nullable | comment                            |
|-------------------|----------|------------------------------------|
| firstname         | Yes      |                                    |
| lastname          | Yes      |                                    |
| username          | Yes      | character must be between 4 and 20 |
| user_category     | Yes      |                                    |
| grade             | Yes      |                                    |
| gender            | Yes      |                                    |
| state_id          | Yes      | If state_id not exists pass as text in state field. |
| state             | Yes      |                                       |
| country_id        | Yes      | If country_id not exists pass as text in country field. |
| country           | Yes      |                                    |
| school_id         | Yes      | If school_id not exists pass as text in school field. |
| school            | Yes      |                                    |
| school_district_id| Yes      | If school_district_id not exists pass as text in school district field. |
| school            | Yes      |                                    |
| thumbnail_path    | Yes      |                                    |
| about_me          | Yes      |                                    |
| course            | Yes      |                                    |
| roster_global_userid | Yes   |                                    |


**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**Request Body**

**sample 1**

```json
{
    "country_id": "1032b612-b6da-4f5a-95db-1a1cc6489e7b",
    "school_id": "5232b612-b6da-4f5a-95db-1a1cc6489e7b",
    "school_district_id": "1232b612-b6da-4f5a-95db-1a1cc6489e7b",
    "state_id": "9232b612-b6da-4f5a-95db-1a1cc6489e7b",
    "gender": "male",
    "grade": ["5", "9"],
    "course": [{
        "code": "MA#MA6",
        "name": "Math 6"
    }],
    "about_me": "I am currently a Research Assistant",
    "roster_global_userid" : "408769"
}
```

**sample 2**

```json
{
    "country": "united states",
    "school": "joseph",
    "school_district": "Colton Joint Unified School District",
    "state": "Califorina",
    "gender": "male",
    "grade": ["5", "9"],
    "course": [{
        "code": "MA#MA6",
        "name": "Math 6"
    }],
    "about_me": "I am currently a Research Assistant",
    "roster_global_userid" : "408769"
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==" -d '{
    "country": "united states",
    "school": "joseph",
    "school_district": "Colton Joint Unified School District",
    "state": "california",
    "gender": "male",
    "grade": [
        "5",
        "9"
    ],
    "course": [
        {
            "code": "MA#MA6",
            "name": "Math 6"
        }
    ],
    "about_me": "I am currently a Research Assistant"
}' -X PUT http://nucleus.gooru.org/api/nucleus-auth/v1/users/me
```

##Fetch user details
**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus-auth/{version}/users/me |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticated access token]
| Request Body Type | None |
| Response Code | 200 Successful |


**Authorization Token**
```
Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw==
```

**Response body**
```json
{
    "birth_date": "1987-11-11",
    "created_at": "2016-01-21T11:58:11Z",
    "email_id": "mygooru@gmail.com",
    "firstname": "gooru",
    "grade": ["5", "9"],
    "id": "95f0da69-fb0e-401a-8c9c-37acdba55ff0",
    "lastname": "learning",
    "updated_at": "2016-01-21T11:58:11Z",
    "user_category": "teacher",
    "username": "gooru",
    "roster_global_userid" : "40th"
}
```
**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X GET http://nucleus.gooru.org/api/nucleus-auth/v1/users/me
```
##Find user by username and email Id

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus-auth/{version}/users |
| Auth | Required |
| Authorization Header | Authorization: Token [anonymous access token]
| Request Body Type | None |
| Response Code | 200 Successful |

**Request Param**

| Name      | Comment  |
|---------- |----------|
| username  | Either  username or email id is mandatory |
| email     | Either  username or email id is mandatory |



**Authorization Token**
```
Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw==
```

**Response body**
```json
{
    "user_id": "95f0da69-fb0e-401a-8c9c-37acdba55ff0",
    "username": "gooru",
    "email_id": "mygooru@gmail.com"
}
```

**Response Body**

```json
{
    "created_at": 1333284935000,
    "email_id": "sachin.gurukul@gmail.com",
    "firstname": "sachin",
    "gender": "male",
    "id": "df3f48c7-3ba3-4499-9eea-d07c067333b4",
    "lastname": "vats",
    "login_type": "credential",
    "provision_type": "registered",
    "updated_at": 1365127819000,
    "user_category": "teacher",
    "username": "sachin"
}
```

**Response  Attributes**

| Name      | Comment  |
|---------- |----------|
| login_type  | support types are :  'google', 'wsfed', 'saml', and 'credential' |
| provision_type  | support types are : 'google', 'wsfed', 'saml', and 'registered' |

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X GET http://nucleus.gooru.org/api/nucleus-auth/v1/users?username=gooru
```

##Forgot password

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/nucleus-auth/{version}/users/password-reset |
| Auth | Required |
| Request Body Type | JSON |
| Authorization Header | Authorization: Token [anonymous access token]
| Response Code | 200 Successful |

**Authorization Token**
```
Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ==

```

**Request Body fields**

| Name      | Nullable | Comment  |
|---------- |----------|----------|
| email_id  | No       |          |


**Request body**
```json
{
    "email_id": "mygooru@gmail.com"
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ=="  -X POST -d '{
    "email_id": "mygooru@gmail.com"
}' http://nucleus.gooru.org/api/nucleus-auth/v1/users/password-reset
```

##Reset un-authenticate user password

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /api/nucleus-auth/{version}/users/{user_id}/password |
| Auth | Required |
| Request Body Type | JSON |
| Authorization Header | Authorization: Token [anonymous access token]
| Response Code | 204 No content |

**Authorization Token**
```
Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ==
```

**Request Body fields**

| Name      | Nullable | Comment  |
|-----------|----------|----------|
| new_password  | No       |          |
| token     | No       |          |


**Request body**
```json
{
    "token": "UkVTRVRfUEFTU1dPUkRfVE9LRU46VGh1IEphbiAyMSAyMDowMzowOCBJU1QgMjAxNjoxNDUzMzg2Nzg4NTg5",
    "new_password": "newgooru123"
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ=="  -X PUT -d '{
    "token": "UkVTRVRfUEFTU1dPUkRfVE9LRU46VGh1IEphbiAyMSAyMDowMzowOCBJU1QgMjAxNjoxNDUzMzg2Nzg4NTg5",
    "new_password": "newgooru123"
}' http://nucleus.gooru.org/api/nucleus-auth/v1/users/95f0da69-fb0e-401a-8c9c-37acdba55ff0/password
```

##Reset authenticate user password

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /api/nucleus-auth/{version}/users/me/password |
| Auth | Required |
| Request Body Type | JSON |
| Authorization Header | Authorization: Token [Authenticated access token]
| Response Code | 204 No content |

**Authorization Token**
```
Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw==
```

**Request Body fields**

| Name         | Nullable | Comment  |
|--------------|----------|----------|
| old_password | No       |          |
| new_password | No       |          |


**Request body**
```json
{
    "new_password": "gooru1234",
    "old_password": "newgooru123"
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X PUT -d '{
    "new_password": "gooru1234",
    "old_password": "newgooru123"
}' http://nucleus.gooru.org/api/nucleus-auth/v1/users/me/password
```

##Change  email address

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/nucleus-auth/{version}/users/me/email |
| Auth | Required |
| Request Body Type | JSON |
| Authorization Header | Authorization: Token [Authenticate access token]
| Response Code | 200 Successful |

**Authorization Token**
```
Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw==

```

**Request Body fields**

| Name      | Nullable | Comment  |
|---------- |----------|----------|
| email_id  | No       |          |


**Request body**
```json
{
    "email_id": "mygooru@gooru.org"
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X POST -d '{
    "email_id": "mygooru@gooru.org"
}' http://nucleus.gooru.org/api/nucleus-auth/v1/users/me/email
```

##Confirm  email address.

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /api/nucleus-auth/{version}/users/{user_id}/email |
| Auth | Required |
| Request Body Type | JSON |
| Authorization Header | Authorization: Token [anonymous access token]
| Response Code | 204 No content |

**Authorization Token**

```
Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ==
```

**Request Body fields**

| Name      | Nullable | Comment  |
|---------- |----------|----------|
| token  | No       |          |


**Request Body**

```json
{
    "token": "RU1BSUxfQ09ORklSTV9UT0tFTjpGcmkgSmFuIDIyIDExOjM3OjU3IElTVCAyMDE2OjE0NTM0NDI4NzcwNjk="
}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ==" -d '{
    "token": "RU1BSUxfQ09ORklSTV9UT0tFTjpGcmkgSmFuIDIyIDExOjM3OjU3IElTVCAyMDE2OjE0NTM0NDI4NzcwNjk="
}'  -X PUT  http://nucleus.gooru.org/api/nucleus-auth/v1/users/95f0da69-fb0e-401a-8c9c-37acdba55ff0/email
```


##Resend confirmation email.

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /api/nucleus-auth/{version}/users/me/email-confirmation |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticate access token]
| Response Code | 200 Successful |

**Authorization Token**

```
Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw==

```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X POST  http://nucleus.gooru.org/api/nucleus-auth/v1/users/me/email-confirmation
```


##Find users by id.

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus-auth/{version}/users |
| Auth | Required |
| Authorization Header | Authorization: Token [anonymous access token]
| Response Code | 200 Successful |

**Request Param**

| Name      | Comment  |
|---------- |----------|
| ids  | It will accept multiple user ids in comma separator format. maximum 30 ids per request |


**Authorization Token**

```
Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw==

```
**Response Body**

```json

{
    "users": [{
        "firstname": "Daniel",
        "id": "00017c7a-7105-4dd8-b3fd-292102583ab8",
        "lastname": "Leffman",
        "username": "danleffman",
        "thumbnail_path" : "00017c7a-7105-4dd8-b3fd-292102583ab8.png"
    }, {
        "firstname": "first",
        "id": "0001cde4-39b2-4a9a-b795-1577d569e375",
        "lastname": "last",
        "username": "ebeystrum@parmaschools.org"
    }, {
        "firstname": "Kadie",
        "id": "00030281-90ed-4d82-8f05-986b858642d2",
        "lastname": "Busby",
        "username": "KadieB"
    }]
}

```
- If there is no thumbnail image for user, "thumbnail_path" will not present in response. 

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token MTQ1OTgzOTA3MDM3NToyMWRmMDVhYS02MjY0LTRiNmQtYmZmMS03NDkyOGMwNzI2MjU6YmE5NTZhOTctYWUxNS0xMWU1LWEzMDItZjhhOTYzMDY1OTc2" -X GET http://nucleus.gooru.org/api/nucleus-auth/v1/users?ids=00030281-90ed-4d82-8f05-986b858642d2,00017c7a-7105-4dd8-b3fd-292102583ab8,0001cde4-39b2-4a9a-b795-1577d569e375
```
