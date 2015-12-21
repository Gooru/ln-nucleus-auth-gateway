package org.gooru.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteAuthenticationConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {

    final EventBus eb = vertx.eventBus();

    final long mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30L);
    
    router.post(RouteConstants.EP_AUTH_TOKEN).handler(routingContext -> {
      DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader("mb.operation", "create.access.token");
      eb.send(MessagebusEndpoints.MBEP_AUTHENTICATION, new JsonObject(), options, reply -> {
        if (reply.succeeded()) {
          // TODO: Even if we got a response, we need to render it correctly as we may have to send the errors or exceptions
          routingContext.response().end(reply.result().body().toString());
        } else {
          LOG.error("Not able to send message", reply.cause());
          routingContext.response().setStatusCode(500).end();
        }
      });
    });
  }

}
