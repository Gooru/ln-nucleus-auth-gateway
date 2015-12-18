package org.gooru.auth.gateway.routes;

import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.HttpConstants;
import org.gooru.auth.gateway.constants.MessageConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.gooru.auth.gateway.responses.auth.AuthPrefsResponseHolder;
import org.gooru.auth.gateway.responses.auth.AuthPrefsResponseHolderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpMethod;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class RouteAuthConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.nucleus.gateway.bootstrap.ServerVerticle");

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    
    EventBus eBus = vertx.eventBus();
    final long mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30L);

    
    router.route(RouteConstants.API_AUTH_ROUTE).handler(routingContext -> {
      String sessionToken = routingContext.request().getHeader(HttpConstants.HEADER_AUTH);
      // If the session token is null or absent, we send an error to client
      if (sessionToken == null || sessionToken.isEmpty()) {
        routingContext.response().setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode())
          .setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage())
          .end();
      } else {        
        // If the session token is present, we send it to Message Bus for validation
        DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout)
                .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_AUTH_WITH_PREFS)
                .addHeader(MessageConstants.MSG_HEADER_TOKEN, sessionToken);
        eBus.send(MessagebusEndpoints.MBEP_AUTH, null, options, reply -> {
          if (reply.succeeded()) {
            AuthPrefsResponseHolder responseHolder = new AuthPrefsResponseHolderBuilder(reply.result()).build();
            // Message header would indicate whether the auth was successful or not. In addition, successful auth may have been
            // for anonymous user. We allow only GET request for anonymous user (since we do not support head, trace, options etc so far)
            if (responseHolder.isAuthorized()) {
              if(!routingContext.request().method().equals(HttpMethod.GET) && responseHolder.isAnonymous()) {
                routingContext.response().setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode())
                .setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage())
                .end();                
              } else {                
                JsonObject prefs = responseHolder.getPreferences();
                routingContext.put(MessageConstants.MSG_KEY_PREFS, prefs);
                routingContext.next();
              }
            } else {
              routingContext.response().setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode())
              .setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage())
              .end();              
            }
          } else {
            LOG.error("Not able to send message", reply.cause());
            routingContext.response().setStatusCode(HttpConstants.HttpStatus.ERROR.getCode()).end();
          }
        });
      }
    });


  }

}
