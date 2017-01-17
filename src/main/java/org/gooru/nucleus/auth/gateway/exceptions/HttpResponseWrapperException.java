/**
 * 
 */
package org.gooru.nucleus.auth.gateway.exceptions;

import org.gooru.nucleus.auth.gateway.constants.HttpConstants;

import io.vertx.core.json.JsonObject;

/**
 * @author szgooru Created On: 02-Jan-2017
 *
 */
public class HttpResponseWrapperException extends RuntimeException {
    private final HttpConstants.HttpStatus status;
    private final JsonObject payload;

    public HttpResponseWrapperException(HttpConstants.HttpStatus status, JsonObject payload) {
        this.status = status;
        this.payload = payload;
    }

    public HttpResponseWrapperException(HttpConstants.HttpStatus status, String message) {
        this.status = status;
        this.payload = new JsonObject().put("message", message);
    }

    public int getStatus() {
        return this.status.getCode();
    }

    public JsonObject getBody() {
        return this.payload;
    }
}
