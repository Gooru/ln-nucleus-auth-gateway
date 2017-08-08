package org.gooru.nucleus.auth.gateway.constants;

/**
 * Created by ashish on 4/12/15. Constant definition that are used to read
 * configuration
 */
public final class ConfigConstants {

    public static final String HTTP_PORT = "http.port";
    public static final String METRICS_PERIODICITY = "metrics.periodicity.seconds";
    public static final String MBUS_TIMEOUT = "message.bus.send.timeout.milliseconds";
    public static final String MAX_REQ_BODY_SIZE = "request.body.size.max.mb";

    private ConfigConstants() {
        throw new AssertionError();
    }
}
