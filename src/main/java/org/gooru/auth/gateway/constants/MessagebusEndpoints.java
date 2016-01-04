package org.gooru.auth.gateway.constants;

/**
 * Created by ashish on 4/12/15. It contains the definition for the
 * "Message Bus End Points" which are addresses on which the consumers are
 * listening. Note that these definitions are for gateway, and each end point
 * would be defined in their own component as well. This means that if there is
 * any change here, there must be a corresponding change in the consumer as
 * well.
 *
 */
public class MessagebusEndpoints {
  public static final String MBEP_AUTHENTICATION = "org.gooru.auth.message.bus.authentication";
  public static final String MBEP_AUTHORIZE = "org.gooru.auth.message.bus.authorize";
  
  public static final String MBEP_PREFS = "org.gooru.auth.message.bus.prefs";
  public static final String MBEP_METRICS = "org.gooru.auth.message.bus.metrics";
}
