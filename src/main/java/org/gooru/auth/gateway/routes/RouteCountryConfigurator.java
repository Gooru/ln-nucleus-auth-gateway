package org.gooru.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.auth.gateway.constants.CommandConstants;
import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.MessageConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.gooru.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteCountryConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private EventBus eb = null;
  private long mbusTimeout = 30000L;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30000L);
    router.get(RouteConstants.EP_NUCLUES_AUTH_COUNTRIES).handler(this::getStates);
  }

  private void getStates(RoutingContext routingContext) {
    DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.LIST_COUNTRY);
    eb.send(MessagebusEndpoints.MBEP_COUNTRY, RouteRequestUtility.getBodyForMessage(routingContext), options, reply -> {
      RouteResponseUtility.responseHandler(routingContext, reply, LOG);
    });
  }
}
