Features
----------

  -  Update user preference
  -  Get user preference
  
###Update user preference

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | PUT |
| End Point | /nucleus-auth/{version}/users/me/prefs |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticated access token]
| Request Body Type | JSON |
| Response Code | 204 No content |

**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**Request body json fields**

| Field name          | Nullable | comment                            |
|---------------------|----------|------------------------------------|
| standard_preference | No   | it should have details about user preferred subject taxonomy framework  |
| profile_visibility | No   | |



**Request Body**

```json
{"standard_preference" : {"taxonomy": [{"subject": "MA", "standard_framework_code" : "TEXAS"}], "profile_visibility": true}}
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==" -d '{"standard_preference" : {"taxonomy": [{"subject": "MA", "standard_framework_code" : "TEXAS"}], "profile_visibility": true}}' -X PUT http://127.0.0.1:8080/nucleus-auth/v1/users/me/prefs
```


