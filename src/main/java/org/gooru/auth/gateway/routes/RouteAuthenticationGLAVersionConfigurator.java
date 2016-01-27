package org.gooru.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
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

class RouteAuthenticationGLAVersionConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
    router.post(RouteConstants.EP_NUCLUES_AUTH_GLA_VERSION_ANONYMOUS_LOGIN).handler(this::anonymousCreateAccessToken);
    router.post(RouteConstants.EP_NUCLUES_AUTH_GLA_VERSION_LOGIN).handler(this::createAccessToken);

  }

  private void anonymousCreateAccessToken(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                    CommandConstants.ANONYMOUS_CREATE_ACCESS_TOKEN);
    String host = request.getHeader(HttpConstants.HEADER_HOST);
    String referer = request.getHeader(HttpConstants.HEADER_REFERER);
    if (host != null) {
      options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, host);
    } else if (referer != null) {
      options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, referer);
    }
    String apiKey = request.getHeader(HttpConstants.GOORU_API_KEY);
    if (apiKey != null) {
      options.addHeader(MessageConstants.MSG_HEADER_API_KEY, apiKey);
    }
    eb.send(MessagebusEndpoints.MBEP_GLA_VERSION_AUTHENTICATION, RouteRequestUtility.getBodyForMessage(routingContext), options, reply -> {
      RouteResponseUtility.responseHandler(routingContext, reply, LOG);
    });
  }

  private void createAccessToken(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.CREATE_ACCESS_TOKEN);
    String host = request.getHeader(HttpConstants.HEADER_HOST);
    String referer = request.getHeader(HttpConstants.HEADER_REFERER);
    if (host != null) {
      options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, host);
    } else if (referer != null) {
      options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, referer);
    }
    String apiKey = request.getHeader(HttpConstants.GOORU_API_KEY);
    if (apiKey != null) {
      options.addHeader(MessageConstants.MSG_HEADER_API_KEY, apiKey);
    }
    eb.send(MessagebusEndpoints.MBEP_GLA_VERSION_AUTHENTICATION, RouteRequestUtility.getBodyForMessage(routingContext), options, reply -> {
      RouteResponseUtility.responseHandler(routingContext, reply, LOG);
    });
  }

}
