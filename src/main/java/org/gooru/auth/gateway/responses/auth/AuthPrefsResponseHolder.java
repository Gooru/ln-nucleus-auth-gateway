package org.gooru.auth.gateway.responses.auth;

import io.vertx.core.json.JsonObject;

public interface AuthPrefsResponseHolder extends AuthResponseHolder {
  public JsonObject getPreferences();
}
