package org.gooru.nucleus.auth.gateway.responses.auth;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import org.gooru.nucleus.auth.gateway.constants.MessageConstants;

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
        return message.body().toString();
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
        JsonObject jsonObject = (JsonObject) message.body();
        String userId = jsonObject.getString(MessageConstants.MSG_USER_ID);
        return !(userId != null && !userId.isEmpty() && !userId.equalsIgnoreCase(MessageConstants.MSG_USER_ANONYMOUS));
    }

}
