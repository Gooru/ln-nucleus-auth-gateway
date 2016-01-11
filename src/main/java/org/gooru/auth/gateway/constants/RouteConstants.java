package org.gooru.auth.gateway.constants;

/**
 * Created by gooru.
 */
public class RouteConstants {

  // Helper constants
  public static final String API_VERSION = "v1";
  public static final String API_BASE_ROUTE = "/nucleus-auth/" + API_VERSION + "/";
  public static final String API_NUCLUES_AUTH_ROUTE = "/nucleus-auth/*";

  // Helper: Entity name constants

  // Helper: Operations
  public static final String TOKEN = "token";
  public static final String AUTHORIZE = "authorize";
  public static final String USERS = "users";
  public static final String USER_ID = ":userId";
  public static final String USER_PREFERENCES = "prefs";
  

  // Misc helpers
  public static final String SEP = "/";

  // Actual End Point Constants: Note that constant values may be duplicated but
  // we are going to have individual constant values to work with for each
  // point instead of reusing the same

  public static final String EP_NUCLUES_AUTH_TOKEN = API_BASE_ROUTE + TOKEN;

  public static final String EP_NUCLUES_AUTH_AUTHORIZE = API_BASE_ROUTE + AUTHORIZE;

  public static final String EP_NUCLUES_AUTH_USER = API_BASE_ROUTE + USERS;
  public static final String EP_NUCLUES_AUTH_USER_ID = API_BASE_ROUTE + USERS + SEP + USER_ID;
  public static final String EP_NUCLUES_AUTH_USERS = API_BASE_ROUTE + USERS;
  

  // User preference
  public static final String EP_NUCLUES_AUTH_USER_ID_PREFS = API_BASE_ROUTE + USERS + SEP + USER_ID + SEP + USER_PREFERENCES;

}
