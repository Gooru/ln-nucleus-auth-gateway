package org.gooru.auth.gateway.routes.utils;

import java.util.Map.Entry;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RequestParser {

  private final static String REQUEST_BODY = "request.body";

  private final static String REQUEST_PARAMS = "request.params";

  public static final JsonObject getData(RoutingContext routingContext) {
    JsonObject data = new JsonObject();
    MultiMap params = routingContext.request().params();
    if (params != null && params.size() > 0) {
      JsonObject paramsAsJson = new JsonObject();
      for (Entry<String, String> param : params) {
        paramsAsJson.put(param.getKey(), param.getValue());
      }
      data.put(REQUEST_PARAMS, paramsAsJson);
    }

    if (routingContext.getBodyAsJson() != null) {
      data.put(REQUEST_BODY, routingContext.getBodyAsJson());
    }
    return data;
  }

}
