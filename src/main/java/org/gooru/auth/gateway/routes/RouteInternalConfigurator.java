package org.gooru.auth.gateway.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.Router;

class RouteInternalConfigurator implements RouteConfigurator {

  static final Logger LOG = LoggerFactory.getLogger("org.gooru.nucleus.gateway.bootstrap.ServerVerticle");

  @Override
  public void configureRoutes(Vertx vertx, Router router, JsonObject config) {
    router.route("/banner").handler(routingContext -> {
      JsonObject result = new JsonObject().put("Organisation", "gooru.org").put("Product", "nucleus").put("purpose", "api")
        .put("mission", "Honor the human right to education");
      routingContext.response().end(result.toString());
    });
    
    final MetricsService metricsService = MetricsService.create(vertx);
    router.route("/metrics").handler(routingContext -> {
      JsonObject ebMetrics = metricsService.getMetricsSnapshot(vertx);
      routingContext.response().end(ebMetrics.toString());
    });
  }
}
