package org.gooru.nucleus.auth.gateway.constants;

/**
 * It contains the definition for the "Message Bus End Points" which are
 * addresses on which the consumers are listening. Note that these definitions
 * are for gateway, and each end point would be defined in their own component
 * as well. This means that if there is any change here, there must be a
 * corresponding change in the consumer as well.
 */
public final class MessagebusEndpoints {
    public static final String MBEP_AUTH_HANDLER = "org.gooru.nucleus.auth.message.bus.auth.handler";
    public static final String MBEP_GOOGLE_DRIVE = "org.gooru.nucleus.auth.message.bus.google.drive";
    public static final String MBEP_METRICS = "org.gooru.nucleus.auth.message.bus.metrics";

    private MessagebusEndpoints() {
        throw new AssertionError();
    }
}
