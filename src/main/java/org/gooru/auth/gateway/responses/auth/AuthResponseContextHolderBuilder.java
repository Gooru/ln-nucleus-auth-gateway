package org.gooru.auth.gateway.responses.auth;

import io.vertx.core.eventbus.Message;

public class AuthResponseContextHolderBuilder {
  private Message<Object> message;
  
  public AuthResponseContextHolderBuilder(Message<Object> message) {
    this.message = message;
  }
  
  public AuthResponseContextHolder build() {
    return new AuthMessageBusJsonResponseContextHolder(message);
  }
}
