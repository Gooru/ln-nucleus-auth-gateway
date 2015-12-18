package org.gooru.auth.gateway.routes;

import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

class RouteResourceConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.nucleus.gateway.bootstrap.ServerVerticle");

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    
    final EventBus eb = vertx.eventBus();

    final long mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30L);

    
    router.get(RouteConstants.EP_RESOURCE_GET).handler(routingContext -> {
      String resourceId = routingContext.request().getParam(RouteConstants.ID_RESOURCE);
      DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader("mb.operation", "resource.get")
              .addHeader(RouteConstants.ID_RESOURCE, resourceId);
      eb.send(MessagebusEndpoints.MBEP_RESOURCE, new JsonObject(), options, reply -> {
        if (reply.succeeded()) {
          // TODO: Even if we got a response, we need to render it correctly as we may have to send the errors or exceptions
          routingContext.response().end(reply.result().body().toString());
        } else {
          LOG.error("Not able to send message", reply.cause());
          routingContext.response().setStatusCode(500).end();
        }
      });
    });
    
    router.post(RouteConstants.EP_RESOURCE_CREATE).handler(routingContext -> {
      DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader("mb.operation", "resource.create");
      eb.send(MessagebusEndpoints.MBEP_RESOURCE, routingContext.getBodyAsJson(), options, reply -> {
        if (reply.succeeded()) {
          // TODO: Even if we got a response, we need to render it correctly as we may have to send the errors or exceptions
          routingContext.response().end(reply.result().body().toString());
        } else {
          LOG.error("Not able to send message", reply.cause());
          routingContext.response().setStatusCode(500).end();
        }
      });      
    });
    
    
    router.put(RouteConstants.EP_RESOURCE_UPDATE).handler(routingContext -> {
      
    });
  }

}
