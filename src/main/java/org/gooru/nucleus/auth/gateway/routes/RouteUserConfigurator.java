package org.gooru.nucleus.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nucleus.auth.gateway.constants.*;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteUserConfigurator implements RouteConfigurator {

    private static final Logger LOG = LoggerFactory
        .getLogger("org.gooru.nucleus.auth.gateway.bootstrap.ServerVerticle");

    private EventBus eb = null;
    private long mbusTimeout;

    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
        router.post(RouteConstants.EP_NUCLUES_AUTH_USER).handler(this::createUser);
        router.put(RouteConstants.EP_NUCLUES_AUTH_USER_ID).handler(this::updateUser);
        router.get(RouteConstants.EP_NUCLUES_AUTH_USER_ID).handler(this::getUser);
        router.get(RouteConstants.EP_NUCLUES_AUTH_USERS).handler(this::findUser);
        router.put(RouteConstants.EP_NUCLUES_AUTH_PASSWORD).handler(this::updatePassword);
        router.post(RouteConstants.EP_NUCLUES_AUTH_RESET_PASSWORD).handler(this::resetPassword);
        router.put(RouteConstants.EP_NUCLUES_AUTH_EMAIL).handler(this::confirmEmailAddress);
        router.post(RouteConstants.EP_NUCLUES_AUTH_EMAIL).handler(this::resetEmailAddress);
        router.post(RouteConstants.EP_NUCLUES_AUTH_EMAIL_CONFIRM).handler(this::resendConfirmEmail);
    }

    private void createUser(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.CREATE_USER);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void updateUser(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.UPDATE_USER);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void getUser(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.GET_USER);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void findUser(RoutingContext routingContext) {
        final String ids = routingContext.request().getParam(MessageConstants.MSG_USER_IDS);
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                (ids != null && !ids.isEmpty()) ? CommandConstants.GET_USERS_FIND : CommandConstants.GET_USER_FIND);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void updatePassword(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.UPDATE_PASSWORD);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void resetPassword(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.RESET_PASSWORD);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void confirmEmailAddress(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.CONFIRMATION_EMAIL);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void resetEmailAddress(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.RESET_EMAIL_ADDRESS);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

    private void resendConfirmEmail(RoutingContext routingContext) {
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.RESEND_CONFIRMATION_EMAIL);
        eb.send(MessagebusEndpoints.MBEP_USER, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }

}
