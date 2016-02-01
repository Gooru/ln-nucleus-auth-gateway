package org.gooru.auth.gateway.responses.auth;

public interface AuthResponseHolder {
  boolean isAuthorized();

  boolean isAnonymous();
}
