package org.gooru.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.auth.gateway.constants.CommandConstants;
import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.HttpConstants;
import org.gooru.auth.gateway.constants.MessageConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.gooru.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteUserPrefsConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private EventBus eb = null;
  private long mbusTimeout = 30000L;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30000L);
    router.put(RouteConstants.EP_NUCLUES_AUTH_USER_ID_PREFS).handler(this::updateUserPreference);
    router.get(RouteConstants.EP_NUCLUES_AUTH_USER_ID_PREFS).handler(this::getUserPreference);
  }

  private void updateUserPreference(RoutingContext routingContext) {
    DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.UPDATE_USER_PREFERENCE);
    String authorization = routingContext.request().getHeader(HttpConstants.HEADER_AUTH);
    String token = authorization.substring(HttpConstants.TOKEN.length()).trim();
    options.addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
    eb.send(MessagebusEndpoints.MBEP_USER_PREFS, RouteRequestUtility.getBodyForMessage(routingContext), options, reply -> {
      RouteResponseUtility.responseHandler(routingContext, reply, LOG);
    });
  }

  private void getUserPreference(RoutingContext routingContext) {
    DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.GET_USER_PREFERENCE);
    eb.send(MessagebusEndpoints.MBEP_USER_PREFS, RouteRequestUtility.getBodyForMessage(routingContext), options, reply -> {
      RouteResponseUtility.responseHandler(routingContext, reply, LOG);
    });
  }
}
