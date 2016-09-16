package org.gooru.nucleus.auth.gateway.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nucleus.auth.gateway.constants.CommandConstants;
import org.gooru.nucleus.auth.gateway.constants.ConfigConstants;
import org.gooru.nucleus.auth.gateway.constants.HttpConstants;
import org.gooru.nucleus.auth.gateway.constants.MessageConstants;
import org.gooru.nucleus.auth.gateway.constants.MessagebusEndpoints;
import org.gooru.nucleus.auth.gateway.constants.RouteConstants;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteRequestUtility;
import org.gooru.nucleus.auth.gateway.routes.utils.RouteResponseUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RouteInternalConfigurator implements RouteConfigurator {

    static final Logger LOG = LoggerFactory.getLogger("org.gooru.nucleus.auth.gateway.bootstrap.ServerVerticle");
    private EventBus eb = null;
    private long mbusTimeout;
    
    @Override
    public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
        eb = vertx.eventBus();
        mbusTimeout = config.getLong(ConfigConstants.MBUS_TIMEOUT, RouteConstants.DEFAULT_TIMEOUT);
        
        router.route("/banner").handler(
            routingContext -> {
                JsonObject result =
                    new JsonObject().put("Organisation", "gooru.org").put("Product", "auth")
                        .put("purpose", "authentication").put("mission", "Honor the human right to education");
                routingContext.response().end(result.toString());
            });

        final MetricsService metricsService = MetricsService.create(vertx);
        router.route("/metrics").handler(routingContext -> {
            JsonObject ebMetrics = metricsService.getMetricsSnapshot(vertx);
            routingContext.response().end(ebMetrics.toString());
        });
        
        router.post(RouteConstants.EP_INTERNAL_AUTHENTICATE).handler(this::authenticate);
        router.post(RouteConstants.EP_INTERNAL_IMPERSONATE).handler(this::impersonate);
    }
    
    private void authenticate(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        String authorization = request.getHeader(HttpConstants.HEADER_AUTH);
        String basicAuthCredentials = null;
        if (authorization != null && authorization.startsWith(HttpConstants.BASIC)) {
            basicAuthCredentials = authorization.substring(HttpConstants.BASIC.length()).trim();
        }
        
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.INTERNAL_AUTHENTICATE).addHeader(MessageConstants.MSG_HEADER_BASIC_AUTH, basicAuthCredentials);;
        eb.send(MessagebusEndpoints.MBEP_INTERNAL, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }
    
    private void impersonate(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        String auth = request.getHeader(HttpConstants.HEADER_AUTH);
        String credentials = null;
        if (auth != null && auth.startsWith(HttpConstants.BASIC)) {
            credentials = auth.substring(HttpConstants.BASIC.length()).trim();
        }
        DeliveryOptions options =
            new DeliveryOptions().setSendTimeout(mbusTimeout).addHeader(MessageConstants.MSG_HEADER_OP,
                CommandConstants.INTERNAL_IMPERSONATE).addHeader(MessageConstants.MSG_HEADER_BASIC_AUTH, credentials);
        eb.send(MessagebusEndpoints.MBEP_INTERNAL, RouteRequestUtility.getBodyForMessage(routingContext), options,
            reply -> RouteResponseUtility.responseHandler(routingContext, reply, LOG));
    }
}
