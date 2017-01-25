package org.gooru.nucleus.auth.gateway.routes;

import org.gooru.nucleus.auth.gateway.constants.ConfigConstants;
import org.gooru.nucleus.auth.gateway.constants.HttpConstants;
import org.gooru.nucleus.auth.gateway.constants.MessageConstants;
import org.gooru.nucleus.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.nucleus.auth.gateway.constants.RouteConstants;
import org.gooru.nucleus.auth.gateway.responses.auth.AuthResponseContextHolder;
import org.gooru.nucleus.auth.gateway.responses.auth.AuthResponseContextHolderBuilder;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteAuthUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpMethod;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class RouteAuthConfigurator implements RouteConfigurator {

    private static final Logger LOG =
        LoggerFactory.getLogger("org.gooru.nucleus.auth.gateway.bootstrap.ServerVerticle");

    private long mbusTimeout;
    private EventBus eBus = null;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eBus = vertx.eventBus();
        mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
        router.route(RouteConstants.API_NUCLUES_AUTH_ROUTE).handler(this::validateAccessToken);
    }

    private void validateAccessToken(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();

        if (RouteAuthUtility.isAuthCheckRequired(request)) {
            String authorization = request.getHeader(HttpConstants.HEADER_AUTH);
            String accessToken = null;
            if (authorization != null && authorization.startsWith(HttpConstants.TOKEN)) {
                accessToken = authorization.substring(HttpConstants.TOKEN.length()).trim();
            }

            if (accessToken == null) {
                response.setStatusCode(HttpConstants.HttpStatus.UNAUTHORIZED.getCode())
                    .setStatusMessage(HttpConstants.HttpStatus.UNAUTHORIZED.getMessage()).end();
            } else {
                // If the session token is present, we send it to Message Bus
                // for validation. We stash it on to routing context for good
                // measure. We could
                // have done that later in success callback but we want to avoid
                // closure from callback for success to this local context,
                // hence it is here
                routingContext.put(MessageConstants.MSG_HEADER_TOKEN, accessToken);
                DeliveryOptions options = new DeliveryOptions().setSendTimeout(mbusTimeout)
                    .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_TOKEN_CHECK)
                    .addHeader(MessageConstants.MSG_HEADER_TOKEN, accessToken);
                eBus.send(MessagebusEndpoints.MBEP_AUTH_HANDLER, null, options, reply -> {
                    if (reply.succeeded()) {
                        AuthResponseContextHolder responseHolder =
                            new AuthResponseContextHolderBuilder(reply.result()).build();
                        if (responseHolder.isAuthorized()) {
                            if ((!request.method().name().equals(HttpMethod.GET.name())
                                && (request.method().name().equals(HttpMethod.POST.name())
                                    && !request.uri().endsWith(RouteConstants.SIGNUP)
                                    && !request.uri().endsWith(RouteConstants.RESET_PASSWORD)))
                                && responseHolder.isAnonymous()) {
                                routingContext.response().setStatusCode(HttpConstants.HttpStatus.FORBIDDEN.getCode())
                                    .setStatusMessage(HttpConstants.HttpStatus.FORBIDDEN.getMessage()).end();
                            } else {
                                routingContext.put(MessageConstants.MSG_USER_CONTEXT_HOLDER,
                                    responseHolder.getUserContext());
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
        } else {
            routingContext.next();
        }
    }
}
