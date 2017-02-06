package org.gooru.nucleus.auth.gateway.responses.auth.transformers;

import org.gooru.nucleus.auth.gateway.exceptions.HttpResponseWrapperException;

import io.vertx.core.eventbus.Message;

public final class ResponseTransformerBuilder {
    private ResponseTransformerBuilder() {
        throw new AssertionError();
    }

    public static ResponseTransformer build(Message<Object> message) {
        return new HttpResponseTransformer(message);
    }

    public static ResponseTransformer buildHttpResponseWrapperExceptionBuild(HttpResponseWrapperException ex) {
        return new HttpResponseWrapperExceptionTransformer(ex);
    }
}
