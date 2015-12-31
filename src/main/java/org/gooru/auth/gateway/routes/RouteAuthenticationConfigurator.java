package org.gooru.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.MessageConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.OperationConstants;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.gooru.auth.gateway.routes.utils.RequestParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteAuthenticationConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private EventBus eb = null;
  private long mbusTimeout = 30000L;;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30000L);
    router.post(RouteConstants.EP_AUTH_TOKEN).handler(this::createAccessToken);
    router.delete(RouteConstants.EP_AUTH_TOKEN).handler(this::deleteAccessToken);

  }

  private void createAccessToken(RoutingContext routingContext) {
    DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, OperationConstants.OP_CREATE_AUTH_TOKEN);
    eb.send(MessagebusEndpoints.MBEP_AUTHENTICATION, RequestParser.getData(routingContext), options, reply -> {
      if (reply.succeeded()) {
        routingContext.response().end(reply.result().body().toString());
      } else {
        LOG.error("Not able to send message", reply.cause());
        routingContext.response().setStatusCode(500).end();
      }
    });
  }

  private void deleteAccessToken(RoutingContext routingContext) {
    DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, OperationConstants.OP_DELETE_AUTH_TOKEN);
    eb.send(MessagebusEndpoints.MBEP_AUTHENTICATION, routingContext.getBodyAsJson(), options, reply -> {
      if (reply.succeeded()) {
        routingContext.response().end(reply.result().body().toString());
      } else {
        LOG.error("Not able to send message", reply.cause());
        routingContext.response().setStatusCode(500).end();
      }
    });
  }

}
