package org.gooru.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.gooru.auth.gateway.constants.*;
import org.gooru.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteGoogleDriveConfigurator implements RouteConfigurator {

  private static final Logger LOG = LoggerFactory.getLogger("org.gooru.auth.gateway.bootstrap.ServerVerticle");

  private EventBus eb = null;

  private long mbusTimeout;

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    eb = vertx.eventBus();
    mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
    router.post(RouteConstants.EP_NUCLUES_AUTH_GOOGLE_CONNECT_DRIVE).handler(this::connectGoogleDrive);
    router.get(RouteConstants.EP_NUCLUES_AUTH_GOOGLE_DRIVE_CALLBACK).handler(this::googleDriveCallback);
    router.get(RouteConstants.EP_NUCLUES_AUTH_GOOGLE_DRIVE_REFRESH_TOKEN).handler(this::googleDriveRefreshToken);
    router.delete(RouteConstants.EP_NUCLUES_AUTH_GOOGLE_DRIVE_REFRESH_TOKEN).handler(this::deleteDriveRefreshToken);
  }

  private void connectGoogleDrive(RoutingContext context) {
    DeliveryOptions options =
      new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.CONNECT_GOOGLE_DRIVE);
    eb.send(MessagebusEndpoints.MBEP_GOOGLE_DRIVE, RouteRequestUtility.getBodyForMessage(context), options,
      reply -> RouteResponseUtility.responseHandler(context, reply, LOG));
  }

  private void googleDriveCallback(RoutingContext context) {
    DeliveryOptions options =
      new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.GOOGLE_DRIVE_CALLBACK);
    eb.send(MessagebusEndpoints.MBEP_GOOGLE_DRIVE, RouteRequestUtility.getBodyForMessage(context), options,
      reply -> RouteResponseUtility.responseHandler(context, reply, LOG));
  }

  private void googleDriveRefreshToken(RoutingContext context) {
    DeliveryOptions options =
      new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.GOOGLE_DRIVE_REFRESH_TOKEN);
    eb.send(MessagebusEndpoints.MBEP_GOOGLE_DRIVE, RouteRequestUtility.getBodyForMessage(context), options,
      reply -> RouteResponseUtility.responseHandler(context, reply, LOG));
  }

  private void deleteDriveRefreshToken(RoutingContext context) {
    DeliveryOptions options =
      new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP, CommandConstants.GOOGLE_DRIVE_DELETE_REFRESH_TOKEN);
    eb.send(MessagebusEndpoints.MBEP_GOOGLE_DRIVE, RouteRequestUtility.getBodyForMessage(context), options,
      reply -> RouteResponseUtility.responseHandler(context, reply, LOG));
  }

}
