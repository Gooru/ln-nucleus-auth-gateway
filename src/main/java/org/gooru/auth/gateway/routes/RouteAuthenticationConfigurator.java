package org.gooru.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.gooru.auth.gateway.constants.*;
import org.gooru.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteAuthenticationConfigurator implements RouteConfigurator {

  private static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
    router.post(RouteConstants.EP_NUCLUES_AUTH_TOKEN).handler(this::createAccessToken);
    router.delete(RouteConstants.EP_NUCLUES_AUTH_TOKEN).handler(this::deleteAccessToken);
    router.get(RouteConstants.EP_NUCLUES_AUTH_TOKEN).handler(this::getAccessToken);
  }

  private void createAccessToken(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String authorization = request.getHeader(HttpConstants.HEADER_AUTH);
    DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout);
    String host = request.getHeader(HttpConstants.HEADER_HOST);
    String referer = request.getHeader(HttpConstants.HEADER_REFERER);
    if (host != null) {
      options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, host);
    } else if (referer != null) {
      options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, referer);
    }
    if (authorization != null && authorization.startsWith(HttpConstants.BASIC)) {
      String basicAuthCredentials = authorization.substring(HttpConstants.BASIC.length()).trim();
      options.addHeader(MessageConstants.MSG_HEADER_BASIC_AUTH, basicAuthCredentials);
      options.addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.CREATE_ACCESS_TOKEN);
    } else {
      options.addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.ANONYMOUS_CREATE_ACCESS_TOKEN);
    }
    eb.send(MessagebusEndpoints.MBEP_AUTHENTICATION, RouteRequestUtility.getBodyForMessage(routingContext), options,
      reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }

  private void deleteAccessToken(RoutingContext routingContext) {
    DeliveryOptions options =
      new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.DELETE_ACCESS_TOKEN);
    String authorization = routingContext.request().getHeader(HttpConstants.HEADER_AUTH);
    String token = authorization.substring(HttpConstants.TOKEN.length()).trim();
    options.addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
    eb.send(MessagebusEndpoints.MBEP_AUTHENTICATION, RouteRequestUtility.getBodyForMessage(routingContext), options,
      reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }

  private void getAccessToken(RoutingContext routingContext) {
    DeliveryOptions options =
      new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.GET_ACCESS_TOKEN);
    String authorization = routingContext.request().getHeader(HttpConstants.HEADER_AUTH);
    String token = authorization.substring(HttpConstants.TOKEN.length()).trim();
    options.addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
    eb.send(MessagebusEndpoints.MBEP_AUTHENTICATION, RouteRequestUtility.getBodyForMessage(routingContext), options,
      reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }
}
