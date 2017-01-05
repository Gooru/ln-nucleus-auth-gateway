package org.gooru.nucleus.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nucleus.auth.gateway.constants.*;
import org.gooru.nucleus.auth.gateway.routes.utils.DeliveryOptionsBuilder;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteAuthorizeConfigurator implements RouteConfigurator {

    private static final Logger LOG =
        LoggerFactory.getLogger("org.gooru.nucleus.auth.gateway.bootstrap.ServerVerticle");

    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
        router.post(RouteConstants.EP_NUCLUES_AUTH_AUTHORIZE).handler(this::authorize);

    }

    private void authorize(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_AUTHORIZE);
        String host = request.getHeader(HttpConstants.HEADER_HOST);
        String referer = request.getHeader(HttpConstants.HEADER_REFERER);
        if (host != null) {
            options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, host);
        } else if (referer != null) {
            options.addHeader(MessageConstants.MSG_HEADER_REQUEST_DOMAIN, referer);
        }

        eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

}
