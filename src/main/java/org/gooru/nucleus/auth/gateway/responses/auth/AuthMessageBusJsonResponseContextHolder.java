package org.gooru.nucleus.auth.gateway.responses.auth;

import org.gooru.nucleus.auth.gateway.constants.MessageConstants;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

class AuthMessageBusJsonResponseContextHolder implements AuthResponseContextHolder {

    private final Message<Object> message;
    private boolean isAuthorized = false;

    @Override
    public boolean isAuthorized() {
        return isAuthorized;
    }

    @Override
    public String getUserContext() {
        if (!isAuthorized) {
            return null;
        }
        JsonObject responseBody = (JsonObject) message.body();
        JsonObject userContext = responseBody.getJsonObject(MessageConstants.MSG_HTTP_BODY)
            .getJsonObject(MessageConstants.MSG_HTTP_RESPONSE);
        return userContext != null ? userContext.toString() : null;
    }

    public AuthMessageBusJsonResponseContextHolder(Message<Object> message) {
        this.message = message;
        if (message != null) {
            String result = message.headers().get(MessageConstants.MSG_OP_STATUS);
            if (result != null && result.equalsIgnoreCase(MessageConstants.MSG_OP_STATUS_SUCCESS)) {
                isAuthorized = true;
            }
        }
    }

    @Override
    public boolean isAnonymous() {
        JsonObject jsonObject = new JsonObject(getUserContext());
        String userId = jsonObject.getString(MessageConstants.MSG_USER_ID);
        return !(userId != null && !userId.isEmpty() && !userId.equalsIgnoreCase(MessageConstants.MSG_USER_ANONYMOUS));
    }

}
