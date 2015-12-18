package org.gooru.auth.gateway.responses.auth;

public interface AuthResponseHolder {
  public boolean isAuthorized();
  public boolean isAnonymous();
}
