package org.gooru.nucleus.auth.gateway.constants;

/**
 * It contains the definition for the "Message Bus End Points" which are
 * addresses on which the consumers are listening. Note that these definitions
 * are for gateway, and each end point would be defined in their own component
 * as well. This means that if there is any change here, there must be a
 * corresponding change in the consumer as well.
 */
public class MessagebusEndpoints {
    public static final String MBEP_AUTH = "org.gooru.nucleus.auth.message.bus.auth";
    public static final String MBEP_AUTHENTICATION = "org.gooru.nucleus.auth.message.bus.authentication";
    public static final String MBEP_GLA_VERSION_AUTHENTICATION =
        "org.gooru.nucleus.auth.message.bus.gla.version.authentication";
    public static final String MBEP_AUTHORIZE = "org.gooru.nucleus.auth.message.bus.authorize";
    public static final String MBEP_USER = "org.gooru.nucleus.auth.message.bus.user";
    public static final String MBEP_USER_PREFS = "org.gooru.nucleus.auth.message.bus.user.prefs";
    public static final String MBEP_EVENT = "org.gooru.nucleus.message.bus.publisher.event";
    public static final String MBEP_METRICS = "org.gooru.nucleus.auth.message.bus.metrics";
    public static final String MBEP_GOOGLE_DRIVE = "org.gooru.nucleus.auth.message.bus.google.drive";
    public static final String MBEP_AUTH_CLIENT = "org.gooru.nucleus.auth.message.bus.auth.client";
}
