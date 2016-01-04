package org.gooru.auth.gateway.routes.utils;

import java.util.Map.Entry;

import org.gooru.auth.gateway.constants.MessageConstants;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RouteRequestUtility {

  public static final JsonObject getBodyForMessage(RoutingContext routingContext) {
    JsonObject result = new JsonObject();
    JsonObject httpBody = null;
    if (!routingContext.request().method().name().equals(HttpMethod.GET.name())) {
      httpBody = routingContext.getBodyAsJson();
    }
    if (httpBody != null) {
      result.put(MessageConstants.MSG_HTTP_BODY, httpBody);
    }

    //result.put(MessageConstants.MSG_KEY_PREFS, (JsonObject) routingContext.get(MessageConstants.MSG_KEY_PREFS));
    //result.put(MessageConstants.MSG_USER_ID, (String) routingContext.get(MessageConstants.MSG_USER_ID));

    MultiMap params = routingContext.request().params();
    if (params != null && params.size() > 0) {
      JsonObject paramsAsJson = new JsonObject();
      for (Entry<String, String> param : params) {
        paramsAsJson.put(param.getKey(), param.getValue());
      }
      result.put(MessageConstants.MSG_HTTP_PARAM, paramsAsJson);
    }

    return result;
  }

}
