package org.gooru.auth.gateway.routes;

import io.netty.handler.codec.http.HttpMethod;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.HttpConstants;
import org.gooru.auth.gateway.constants.MessageConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.OperationConstants;
import org.gooru.auth.gateway.constants.ParameterConstants;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.gooru.auth.gateway.responses.auth.AuthPrefsResponseHolder;
import org.gooru.auth.gateway.responses.auth.AuthPrefsResponseHolderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteAuthConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private static final String TOKEN = "Token";

  private long mbusTimeout = 30000L;
  private EventBus eBus = null;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eBus = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30000L);
    router.route(RouteConstants.API_AUTH_ROUTE).handler(BodyHandler.create());
    router.route(RouteConstants.API_AUTH_ROUTE).handler(this::validateClient);
    router.route(RouteConstants.API_AUTH_ROUTE).handler(this::validateAccessToken);
  }

  private void validateClient(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    HttpServerResponse response = routingContext.response();
    if (request.method().equals(HttpMethod.POST) && request.absoluteURI().contains(RouteConstants.EP_AUTH_TOKEN)) {
      // validate client id and secret key is passing from client side
      JsonObject data = routingContext.getBodyAsJson();
      if (data.getString(ParameterConstants.PARAM_CLIENT_ID) == null || data.getString(ParameterConstants.PARAM_CLIENT_SECRET) == null) {
        response.setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode()).setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage())
                .end();
      }
    }
    routingContext.next();
  }

  private void validateAccessToken(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    HttpServerResponse response = routingContext.response();
    String authorization = request.getHeader(HttpConstants.HEADER_AUTH);
    if (authorization == null || !authorization.startsWith(TOKEN)) {
      response.setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode()).setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage())
              .end();
    } else {
      String token = authorization.substring(TOKEN.length()).trim();
      DeliveryOptions options =
              new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, OperationConstants.OP_GET_AUTH_TOKEN)
                      .addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
      eBus.send(
              MessagebusEndpoints.MBEP_AUTHENTICATION,
              null,
              options,
              reply -> {
                routingContext.next();
                if (reply.succeeded()) {
                  AuthPrefsResponseHolder responseHolder = new AuthPrefsResponseHolderBuilder(reply.result()).build();
                  if (responseHolder.isAuthorized()) {
                    if (!routingContext.request().method().equals(HttpMethod.GET) && responseHolder.isAnonymous()) {
                      routingContext.response().setStatusCode(HttpConstants.HttpStatus.FORBIDDEN.getCode())
                              .setStatusMessage(HttpConstants.HttpStatus.FORBIDDEN.getMessage()).end();
                    } else {
                      JsonObject prefs = responseHolder.getPreferences();
                      routingContext.put(MessageConstants.MSG_KEY_PREFS, prefs);
                      routingContext.next();
                    }
                  } else {
                    routingContext.response().setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode())
                            .setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage()).end();
                  }
                } else {
                  LOG.error("Not able to send message", reply.cause());
                  routingContext.response().setStatusCode(HttpConstants.HttpStatus.ERROR.getCode()).end();
                }
              });
    }

  }
}
