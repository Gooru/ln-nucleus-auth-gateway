package org.gooru.auth.gateway.responses.auth;

import org.gooru.auth.gateway.constants.MessageConstants;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

class AuthPrefsMessageBusJsonResponseHolder implements AuthPrefsResponseHolder {

  private Message<Object> message;
  private boolean isAuthorized = false;
  
  @Override
  public boolean isAuthorized() {
    return isAuthorized;
  }

  @Override
  public JsonObject getPreferences() {
    if (!isAuthorized) {      
      return null;
    }
    JsonObject jsonObject = (JsonObject)message.body();
    return jsonObject.getJsonObject(MessageConstants.MSG_KEY_PREFS);
  }

  public AuthPrefsMessageBusJsonResponseHolder(Message<Object> message) {
    this.message = message;
    if (message != null) {
      String result = message.headers().get(MessageConstants.MSG_OP_STATUS);
      if (result != null && result.equalsIgnoreCase(MessageConstants.MSG_OP_STATUS_SUCCESS)) {
        isAuthorized =  true;
      }
    }
  }

  @Override
  public boolean isAnonymous() {
    JsonObject jsonObject = (JsonObject)message.body();
    String userId = jsonObject.getString(MessageConstants.MSG_USER_ID);
    if (userId != null && !userId.isEmpty() && !userId.equalsIgnoreCase(MessageConstants.MSG_USER_ANONYMOUS)) {
      return false;
    }
    return true;
  }
  
}
