package org.gooru.auth.gateway.responses.auth;

import io.vertx.core.eventbus.Message;

public class AuthPrefsResponseHolderBuilder {
  private Message<Object> message;
  
  public AuthPrefsResponseHolderBuilder(Message<Object> message) {
    this.message = message;
  }
  
  public AuthPrefsResponseHolder build() {
    return new AuthPrefsMessageBusJsonResponseHolder(message);
  }
}
