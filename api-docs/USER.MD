Features
----------
- Create user account.
- Create child user account.
- Update user account 
- Get User
- Find user by username and email Id.
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


