/**
 * 
 */
package org.gooru.nucleus.auth.gateway.responses.auth.transformers;

import java.util.HashMap;
import java.util.Map;

import org.gooru.nucleus.auth.gateway.exceptions.HttpResponseWrapperException;

import io.vertx.core.json.JsonObject;

/**
 * @author szgooru
 *
 */
public class HttpResponseWrapperExceptionTransformer implements ResponseTransformer {

    private final HttpResponseWrapperException ex;
    
    HttpResponseWrapperExceptionTransformer(HttpResponseWrapperException ex) {
        this.ex = ex;
    }
    
    @Override
    public void transform() {
        //NOOP
    }

    @Override
    public JsonObject transformedBody() {
        return ex.getBody();
    }

    @Override
    public Map<String, String> transformedHeaders() {
        return new HashMap<>(1);
    }

    @Override
    public int transformedStatus() {
        return ex.getStatus();
    }

}
