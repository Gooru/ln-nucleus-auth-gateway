Features
----------
- Create user account.
- Create child user account.
- Update user account 
- Get User
- Find user by username or email Id.
- Forgot password.
- Reset un-authenticate user password.
- Reset authenticate user password.
- Confirm email.
- Resend confirmation email.
- Update user email.


###Create user account

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /nucleus-auth/{version}/users |
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
{"firstname" : "gooru", "lastname" : "learning", "username" : "gooru", "email_id" : "mygooru@gmail.com", "password" : "mygooru123", "birth_date" : "11/12/1987", "user_category" : "teacher", "gender" : "male", "grade" : ["5", "9"]}
```

**Location Header**

```
Location: /users/95f0da69-fb0e-401a-8c9c-37acdba55ff0
```

**Response Body**

```json
{"user_id":"95f0da69-fb0e-401a-8c9c-37acdba55ff0","username":"gooru","client_id":"ba956a97-ae15-11e5-a302-f8a963065976","provided_at":1453377491783,"cdn_urls":{"profile.cdn":"//profile.gooru.org.com"},"access_token":"OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==" -d '{"firstname" : "gooru", "lastname" : "learning", "username" : "gooru", "email_id" : "mygooru@gmail.com", "password" : "mygooru123", "birth_date" : "11/12/1987", "user_category" : "teacher", "gender" : "male", "grade" : ["5", "9"]}' -X POST http://127.0.0.1:8080/nucleus-auth/v1/users
```

###Create child user account

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /nucleus-auth/{version}/users |
| Auth | Required |
| Authorization Header | Authorization: Token [anonymous access token]
| Request Body Type | JSON |
| Response Code | 201 Created |

**Response body json fields**

| Key name       | Nullable | comment                            |
|----------------|----------|------------------------------------|
| username       | No       | character must be between 4 and 20 |
| parent_email_id| No       |                                    |
| password       | No       | character must be between 5 and 14 |
| user_category  | No       |                                    |
| grade          | Yes      |                                    |


**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**Request Body**

```json
{"username" : "gooruChild" , "parent_email_id" : "mygooru@gmail.com", "password" : "mygooru123", "birth_date" : "11/12/2005", "user_category" : "student"}
```

**Location Header**

```
Location: /users/31d1de5e-88e1-440f-9ecb-b66a0ac39250
```

**Response Body**

```json
{"user_id":"31d1de5e-88e1-440f-9ecb-b66a0ac39250","username":"gooruChild","client_id":"ba956a97-ae15-11e5-a302-f8a963065976","provided_at":1453379506086,"cdn_urls":{"profile.cdn":"//profile.gooru.org.com"},"access_token":"MzFkMWRlNWUtODhlMS00NDBmLTllY2ItYjY2YTBhYzM5MjUwOlRodSBKYW4gMjEgMTg6MDE6NDYgSVNUIDIwMTY6MTQ1MzM3OTUwNjA4Ng=="}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==" -d '{"username" : "gooruChild" , "parent_email_id" : "mygooru@gmail.com", "password" : "mygooru123", "birth_date" : "11/12/2005", "user_category" : "student"}' -X POST http://127.0.0.1:8080/nucleus-auth/v1/users
```

##Update user account 

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /nucleus-auth/{version}/users/me |
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




**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**Request Body**

**sample 1**

```json
{"country" : 5, "school_id" : "5232b612-b6da-4f5a-95db-1a1cc6489e7b", "school_district_id" : "1232b612-b6da-4f5a-95db-1a1cc6489e7b", "state_id" : 4, "gender" : "male", "grade" : ["5", "9"], "course" : [{"code" : "MA#MA6", "name" : "Math 6"}], "about_me" : "I am currently a Research Assistant"}
```

**sample 2**

```json
{"country" : "united states", "school_id" : "joseph", "school_district" : "Colton Joint Unified School District", "gender" : "male", "grade" : ["5", "9"], "course" : [{"code" : "MA#MA6", "name" : "Math 6"}], "about_me" : "I am currently a Research Assistant"}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==" -d '{"country" : "united states", "school" : "joseph", "school_district" : "Colton Joint Unified School District", "state" : "california", "gender" : "male", "grade" : ["5", "9"], "course" : [{"code" : "MA#MA6", "name" : "Math 6"}], "about_me" : "I am currently a Research Assistant"}' -X PUT http://127.0.0.1:8080/nucleus-auth/v1/users/me
```

##Get User
**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /nucleus-auth/{version}/users/me |
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
{"birth_date":"1987-11-11","created_at":"2016-01-21T11:58:11Z","email_id":"mygooru@gmail.com","firstname":"gooru","grade":["5","9"],"id":"95f0da69-fb0e-401a-8c9c-37acdba55ff0","lastname":"learning","updated_at":"2016-01-21T11:58:11Z","user_category":"teacher","username":"gooru"}
```
**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X GET http://127.0.0.1:8080/nucleus-auth/v1/users/me
```
##Find user by username and email Id

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /nucleus-auth/{version}/users |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticated access token]
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
{"user_id":"95f0da69-fb0e-401a-8c9c-37acdba55ff0","username":"gooru","email_id":"mygooru@gmail.com"}
```
**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X GET http://127.0.0.1:8080/nucleus-auth/v1/users?username=gooru
```

##Forgot password

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /nucleus-auth/{version}/users/password-reset |
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
{"email_id":"mygooru@gmail.com"}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ=="  -X POST -d '{"email_id" : "mygooru@gmail.com"}' http://127.0.0.1:8080/nucleus-auth/v1/users/password-reset
```

##Reset un-authenticate user password

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /nucleus-auth/{version}/users/{user_id}/password |
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
{"token" : "UkVTRVRfUEFTU1dPUkRfVE9LRU46VGh1IEphbiAyMSAyMDowMzowOCBJU1QgMjAxNjoxNDUzMzg2Nzg4NTg5", "password" : "newgooru123"}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YW5vbnltb3VzOlR1ZSBKYW4gMTkgMTc6MTU6MTcgSVNUIDIwMTY6MTQ1MzIwMzkxNzY0OQ=="  -X PUT -d '{"token" : "UkVTRVRfUEFTU1dPUkRfVE9LRU46VGh1IEphbiAyMSAyMDowMzowOCBJU1QgMjAxNjoxNDUzMzg2Nzg4NTg5", "new_password" : "newgooru123"}' http://127.0.0.1:8080/nucleus-auth/v1/users/95f0da69-fb0e-401a-8c9c-37acdba55ff0/password
```

##Reset authenticate user password

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /nucleus-auth/{version}/users/me/password |
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
{"new_password" : "gooru1234", "old_password" : "newgooru123"}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X PUT -d '{"new_password" : "gooru1234", "old_password" : "newgooru123"}' http://127.0.0.1:8080/nucleus-auth/v1/users/me/password
```

##Confirm email

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | POST |
| End Point | /nucleus-auth/{version}/users/me/email |
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
{"email_id":"mygooru@gooru.org"}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token OTVmMGRhNjktZmIwZS00MDFhLThjOWMtMzdhY2RiYTU1ZmYwOlRodSBKYW4gMjEgMTc6Mjg6MTEgSVNUIDIwMTY6MTQ1MzM3NzQ5MTc4Mw=="  -X POST -d '{"email_id" : "mygooru@gooru.org"}' http://127.0.0.1:8080/nucleus-auth/v1/users/me/email
```

