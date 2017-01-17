package org.gooru.nucleus.auth.gateway.responses.writers;

import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

import org.gooru.nucleus.auth.gateway.constants.HttpConstants;
import org.gooru.nucleus.auth.gateway.responses.auth.transformers.ResponseTransformer;
import org.gooru.nucleus.auth.gateway.responses.auth.transformers.ResponseTransformerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpServerResponseWriter implements ResponseWriter {
    static final Logger LOG = LoggerFactory.getLogger(ResponseWriter.class);
    private final RoutingContext routingContext;
    ResponseTransformer transformer;

    public HttpServerResponseWriter(RoutingContext routingContext, AsyncResult<Message<Object>> message) {
        this.routingContext = routingContext;
        transformer = ResponseTransformerBuilder.build(message.result());
    }

    public HttpServerResponseWriter(RoutingContext routingContext, ResponseTransformer transformer) {
        this.routingContext = routingContext;
        this.transformer = transformer;
    }

    @Override
    public void writeResponse() {
        final HttpServerResponse response = routingContext.response();
        
        // First set the status code
        writeHttpStatus(response);
        
        // Then set the headers
        writeHttpHeaders(response);
        
        // Then it is turn of the body to be set and ending the response
        writeHttpBody(response);
    }

    private void writeHttpStatus(HttpServerResponse response) {
        response.setStatusCode(transformer.transformedStatus());
    }
    
    private void writeHttpHeaders(HttpServerResponse response) {
        Map<String, String> headers = transformer.transformedHeaders();
        if (headers != null && !headers.isEmpty()) {
            // Never accept content-length from others, we do that
            headers.keySet().stream()
                .filter(headerName -> !headerName.equalsIgnoreCase(HttpConstants.HEADER_CONTENT_LENGTH))
                .forEach(headerName -> response.putHeader(headerName, headers.get(headerName)));
        }
    }
    
    private void writeHttpBody(HttpServerResponse response) {
        final String responseBody =
            ((transformer.transformedBody() != null) && (!transformer.transformedBody().isEmpty())) ? transformer
                .transformedBody().toString() : null;
        if (responseBody != null) {
            response.putHeader(HttpConstants.HEADER_CONTENT_LENGTH, Integer.toString(responseBody.getBytes(StandardCharsets.UTF_8).length));
            response.end(responseBody);
        } else {
            response.end();
        }
    }
}
