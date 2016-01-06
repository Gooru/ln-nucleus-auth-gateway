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

import org.gooru.auth.gateway.constants.CommandConstants;
import org.gooru.auth.gateway.constants.ConfigConstants;
import org.gooru.auth.gateway.constants.HttpConstants;
import org.gooru.auth.gateway.constants.MessageConstants;
import org.gooru.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.auth.gateway.constants.RouteConstants;
import org.gooru.auth.gateway.responses.auth.AuthResponseContextHolder;
import org.gooru.auth.gateway.responses.auth.AuthResponseContextHolderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteAuthConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private long mbusTimeout = 30000L;
  private EventBus eBus = null;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eBus = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, 30000L);
    router.route(RouteConstants.API_NUCLUES_AUTH_ROUTE).handler(this::validateAccessToken);
  }

  private void validateAccessToken(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    HttpServerResponse response = routingContext.response();
    if (!(request.method().name().equalsIgnoreCase(HttpMethod.POST.name()) && (request.uri().contains(RouteConstants.EP_NUCLUES_AUTH_AUTHORIZE)) || request
            .uri().contains(RouteConstants.EP_NUCLUES_AUTH_TOKEN))) {
      String authorization = request.getHeader(HttpConstants.HEADER_AUTH);
      if ((authorization == null || !authorization.startsWith(HttpConstants.TOKEN))) {
        response.setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode()).setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage())
                .end();
      } else {
        String token = authorization.substring(HttpConstants.TOKEN.length()).trim();
        DeliveryOptions options =
                new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.GET_ACCESS_TOKEN)
                        .addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
        eBus.send(
                MessagebusEndpoints.MBEP_AUTH,
                null,
                options,
                reply -> {
                  if (reply.succeeded()) {
                    AuthResponseContextHolder responseHolder = new AuthResponseContextHolderBuilder(reply.result()).build();
                    if (responseHolder.isAuthorized()) {
                      if ((!request.method().equals(HttpMethod.GET) && (request.method().equals(HttpMethod.POST) && !request.uri().contains(
                              RouteConstants.EP_NUCLUES_AUTH_USER)))
                              && responseHolder.isAnonymous()) {
                        routingContext.response().setStatusCode(HttpConstants.HttpStatus.FORBIDDEN.getCode())
                                .setStatusMessage(HttpConstants.HttpStatus.FORBIDDEN.getMessage()).end();
                      } else {
                        routingContext.put(MessageConstants.MSG_USER_CONTEXT_HOLDER, responseHolder.getUserContext());
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
}
