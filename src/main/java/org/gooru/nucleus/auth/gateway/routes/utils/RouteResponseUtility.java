package org.gooru.nucleus.auth.gateway.routes.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nucleus.auth.gateway.responses.writers.ResponseWriterBuilder;
import org.slf4j.Logger;

public final class RouteResponseUtility {

    private RouteResponseUtility() {
        throw new AssertionError();
    }

    public static void responseHandler(final RoutingContext routingContext, final AsyncResult<Message<Object>> reply,
        final Logger LOG) {
        if (reply.succeeded()) {
            ResponseWriterBuilder.build(routingContext, reply).writeResponse();
        } else {
            LOG.error("Not able to send message", reply.cause());
            routingContext.response().setStatusCode(500).end();
        }
    }
}
