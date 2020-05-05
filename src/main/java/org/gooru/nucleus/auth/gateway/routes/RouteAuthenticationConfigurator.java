package org.gooru.nucleus.auth.gateway.routes;

import org.gooru.nucleus.auth.gateway.constants.*;
import org.gooru.nucleus.auth.gateway.routes.utils.DeliveryOptionsBuilder;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

class RouteAuthenticationConfigurator implements RouteConfigurator {

  private static final Logger LOG =
      LoggerFactory.getLogger("org.gooru.nucleus.auth.gateway.bootstrap.ServerVerticle");

  private EventBus eb = null;
  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
    router.post(RouteConstants.EP_NUCLUES_AUTH_TOKEN).handler(this::createAccessToken);
    router.delete(RouteConstants.EP_NUCLUES_AUTH_TOKEN).handler(this::deleteAccessToken);
    router.get(RouteConstants.EP_NUCLUES_AUTH_TOKEN).handler(this::getAccessToken);
    router.delete(RouteConstants.EP_NUCLUES_AUTH_REVOKE_REFRESH_TOKEN).handler(this::deleteRefreshToken);
    
    router.post(RouteConstants.EP_NUCLEUS_USER_SIGNIN).handler(this::createAccessToken);
    router.delete(RouteConstants.EP_NUCLEUS_USER_SIGNOUT).handler(this::deleteAccessToken);
    router.get(RouteConstants.EP_NUCLUES_AUTH_TOKEN).handler(this::getAccessToken);
    router.post(RouteConstants.EP_DOMAIN_BASED_REDIRECT).handler(this::domainBasedRedirect);
    router.post(RouteConstants.EP_INIT_LOGIN).handler(this::initLogin);
  }

  private void createAccessToken(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();
    String authorization = request.getHeader(HttpConstants.HEADER_AUTH);
    DeliveryOptions options =
        DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout);
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
      options.addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_SIGNIN);
    } else if (authorization != null && authorization.startsWith(HttpConstants.REFRESH_TOKEN)) {
      String token = authorization.substring(HttpConstants.REFRESH_TOKEN.length()).trim();
      options.addHeader(MessageConstants.MSG_HEADER_REFRESH_TOKEN, token);
      options.addHeader(MessageConstants.MSG_HEADER_OP,
          MessageConstants.MSG_OP_REFRESH_TOKEN_CREATE_ACCESS_TOKEN);
    } else {
      if (authorization != null && authorization.startsWith(HttpConstants.NONCE)) {
        String nonce = authorization.substring(HttpConstants.NONCE.length()).trim();
        options.addHeader(MessageConstants.MSG_HEADER_NONCE_TOKEN, nonce);
      }
      options.addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_ANONYMOUS_SIGNIN);
    }
    eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER,
        RouteRequestUtility.getBodyForMessage(routingContext), options,
        reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }

  private void deleteAccessToken(RoutingContext routingContext) {
    DeliveryOptions options =
        DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_SIGNOUT);
    String authorization = routingContext.request().getHeader(HttpConstants.HEADER_AUTH);
    String token = authorization.substring(HttpConstants.TOKEN.length()).trim();
    options.addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
    eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER,
        RouteRequestUtility.getBodyForMessage(routingContext), options,
        reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }

  private void getAccessToken(RoutingContext routingContext) {
    DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext)
        .setSendTimeout(mbusTimeout)
        .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_ACCESS_TOKEN_DETAILS);
    String authorization = routingContext.request().getHeader(HttpConstants.HEADER_AUTH);
    String token = authorization.substring(HttpConstants.TOKEN.length()).trim();
    options.addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
    eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER,
        RouteRequestUtility.getBodyForMessage(routingContext), options,
        reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }

  private void domainBasedRedirect(RoutingContext routingContext) {
    DeliveryOptions options =
        DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout);
    options.addHeader(MessageConstants.MSG_HEADER_OP,
        MessageConstants.MSG_OP_DOMAIN_BASED_REDIRECT);
    eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER,
        RouteRequestUtility.getBodyForMessage(routingContext), options,
        reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }

  private void initLogin(RoutingContext routingContext) {
    DeliveryOptions options =
        DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout);
    options.addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_INIT_LOGIN);
    eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER,
        RouteRequestUtility.getBodyForMessage(routingContext), options,
        reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }
  
  private void deleteRefreshToken(RoutingContext routingContext) {
    DeliveryOptions options =
        DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_REFRESH_TOKEN_REVOKE);
    String authorization = routingContext.request().getHeader(HttpConstants.HEADER_AUTH);
    String token = authorization.substring(HttpConstants.TOKEN.length()).trim();
    options.addHeader(MessageConstants.MSG_HEADER_TOKEN, token);
    eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER,
        RouteRequestUtility.getBodyForMessage(routingContext), options,
        reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
  }

}
