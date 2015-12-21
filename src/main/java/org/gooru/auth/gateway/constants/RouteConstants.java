package org.gooru.auth.gateway.constants;

/**
 * Created by ashish on 4/12/15.
 */
public class RouteConstants {

  // Helper constants
  public static final String API_VERSION = "v1";
  public static final String API_BASE_ROUTE = "/auth/" + API_VERSION + "/";
  public static final String API_AUTH_ROUTE = "/auth/*";

  // Helper: Entity name constants

  // Helper: Operations
  public static final String TOKEN = "token";

  // Misc helpers
  public static final String SEP = "/";

  // Actual End Point Constants: Note that constant values may be duplicated but
  // we are going to have individual constant values to work with for each
  // point instead of reusing the same

  public static final String EP_AUTH_TOKEN = API_BASE_ROUTE + TOKEN;

}
