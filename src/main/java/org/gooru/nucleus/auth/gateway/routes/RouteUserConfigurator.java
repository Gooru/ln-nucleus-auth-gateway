package org.gooru.nucleus.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nucleus.auth.gateway.constants.*;
import org.gooru.nucleus.auth.gateway.routes.utils.DeliveryOptionsBuilder;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteUserConfigurator implements RouteConfigurator {

    private static final Logger LOG =
        LoggerFactory.getLogger("org.gooru.nucleus.auth.gateway.bootstrap.ServerVerticle");

    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
        router.post(RouteConstants.EP_NUCLUES_USER_SIGNUP).handler(this::createUser);
        router.put(RouteConstants.EP_NUCLUES_USER_UPDATE).handler(this::updateUser);
        router.put(RouteConstants.EP_NUCLUES_USER_PASSWORD_UPDATE).handler(this::updatePassword);
        router.post(RouteConstants.EP_NUCLUES_USER_RESET_PASSWORD_TRIGGER).handler(this::resetPassword);
    }

    private void createUser(RoutingContext routingContext) {
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_SIGNUP);
        eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void updateUser(RoutingContext routingContext) {
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_UPDATE);
        eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void updatePassword(RoutingContext routingContext) {
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_PASSWORD_UPDATE);
        eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void resetPassword(RoutingContext routingContext) {
        DeliveryOptions options = DeliveryOptionsBuilder.buildWithApiVersion(routingContext).setSendTimeout(mbusTimeout)
            .addHeader(MessageConstants.MSG_HEADER_OP, MessageConstants.MSG_OP_USER_PASSWORD_RESET_TRIGGER);
        eb.send(MessagebusEndpoints.MBEP_AUTH_HANDLER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }
}
