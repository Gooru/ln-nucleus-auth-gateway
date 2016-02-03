Features
----------

  -  Connect to google drive via google oauth with registered client id and secret 
  -  Call back API to obtain state and code from google oauth system. Using state and code obtain refresh token.
  -  Get google drive refresh token 
  -  Delete google drive refresh token
  
###Connect google drive 

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus-auth/{version}/google-drive |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticated access token]
| Request Body Type | JSON |
| Response Code | 302 Moved Permanently |

**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**Request body json fields**

| Field name          | Nullable | comment                            |
|---------------------|----------|------------------------------------|
| callback_url | No   | This is url where API will redirect after successfully obtained refresh token  |

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3Ok1vbiBKYW4gMjUgMTE6MTg6MDUgSVNUIDIwMTY6MTQ1MzcwMDg4NTQzMA==" -d '{"callback_url":"http://localhost:8080/api/nucleus-auth/v1/google-drive/callback"}' -X POST http://localhost:8080/api/nucleus-auth/v1/google-drive
```

###Google authenticate callback

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus-auth/{version}/google-drive/callback |
| Auth | Not required |
| Response Code | 302 Moved Permanently |

**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**NOTE**
This API will be called by the google OAuth service. 


###Get drive refresh token 

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | GET |
| End Point | /api/nucleus-auth/{version}/google-drive/refresh-token |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticated access token]
| Request Body | None |
| Response Code | 200 Successful |

**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3Ok1vbiBKYW4gMjUgMTE6MTg6MDUgSVNUIDIwMTY6MTQ1MzcwMDg4NTQzMA=="  -X GET http://localhost:8080/api/nucleus-auth/v1/google-drive/refresh-token
```

**Request Response**
```
{
    "refresh_token": "1/4VmKupKHy4aMSpoMnsFM-WLD-i4scxrC0GBPeCOtu2U"
}
```



###Delete refresh token

**Rest API details**

| Name | Summary |
|------------------------|--------|
| HTTP Method | DELETE |
| End Point | /api/nucleus-auth/{version}/google-drive/refresh-token |
| Auth | Required |
| Authorization Header | Authorization: Token [Authenticated access token]
| Request Body  | None |
| Response Code | 204 N0 content |

**Authorization Token**
```
Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3OlRodSBKYW4gMjEgMTY6MTE6MTcgSVNUIDIwMTY6MTQ1MzM3Mjg3NzQxMA==
```

**CURL snippet**

```posh
curl -i  -H "Content-Type: application/json" -H "Authorization: Token YWFkYTZhYmMtZWIxMS00NTUwLTk4MWYtM2EzNmE1M2I0OTA3Ok1vbiBKYW4gMjUgMTE6MTg6MDUgSVNUIDIwMTY6MTQ1MzcwMDg4NTQzMA=="  -X DELETE http://localhost:8080/api/nucleus-auth/v1/google-drive/refresh-token
```




