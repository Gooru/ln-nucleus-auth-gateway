package org.gooru.nucleus.auth.gateway.responses.auth.transformers;

import java.util.Map;

import io.vertx.core.json.JsonObject;

public interface ResponseTransformer {
    void transform();

    JsonObject transformedBody();

    Map<String, String> transformedHeaders();

    int transformedStatus();
}
