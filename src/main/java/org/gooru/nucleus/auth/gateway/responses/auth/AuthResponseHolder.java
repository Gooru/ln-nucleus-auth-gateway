package org.gooru.nucleus.auth.gateway.responses.auth;

public interface AuthResponseHolder {
  boolean isAuthorized();

  boolean isAnonymous();
  
}