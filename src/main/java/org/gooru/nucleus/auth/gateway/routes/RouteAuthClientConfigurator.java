package org.gooru.nucleus.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nucleus.auth.gateway.constants.CommandConstants;
import org.gooru.nucleus.auth.gateway.constants.ConfigConstants;
import org.gooru.nucleus.auth.gateway.constants.MessageConstants;
import org.gooru.nucleus.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.nucleus.auth.gateway.constants.RouteConstants;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteAuthClientConfigurator implements RouteConfigurator {

    private static final Logger LOG = LoggerFactory
        .getLogger("org.gooru.nucleus.auth.gateway.bootstrap.ServerVerticle");

    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
        router.post(RouteConstants.EP_NUCLUES_AUTH_CLIENT).handler(this::createAuthClient);
    }

    private void createAuthClient(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.CREATE_AUTH_CLIENT);
        eb.send(MessagebusEndpoints.MBEP_AUTH_CLIENT, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

}
