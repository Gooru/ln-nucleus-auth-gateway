package org.gooru.auth.gateway.constants;

/**
 * Created by gooru.
 */
public class RouteConstants {

  // Helper constants
  private static final String API_VERSION = "v1";
  private static final String API_BASE_ROUTE = "/api/nucleus-auth/" + API_VERSION + "/";
  public static final String API_NUCLUES_AUTH_ROUTE = "/api/nucleus-auth/*";

  // GLA 2.0 Authentication API base route path
  private static final String API_GLA_VERSION_BASE_ROUTE = "/api/nucleus-auth/";
  private static final String LOGIN = "login";
  private static final String ANONYMOUS = "anonymous";

  // Helper: Entity name constants

  // Helper: Operations
  private static final String TOKEN = "token";
  private static final String AUTHORIZE = "authorize";
  private static final String USERS = "users";
  private static final String USER_ID = ":userId";
  private static final String USER_PREFERENCES = "prefs";
  private static final String PASSWORD = "password";
  private static final String RESET_PASSWORD = "password-reset";
  private static final String EMAIL = "email";
  private static final String EMAIL_CONFIRM = "email-confirmation";
  private static final String GOOGLE_DRIVE = "google-drive";
  private static final String CALLBACK = "callback";
  private static final String REFRESH_TOKEN = "refresh-token";

  // Misc helpers
  private static final String SEP = "/";

  // Actual End Point Constants: Note that constant values may be duplicated but
  // we are going to have individual constant values to work with for each
  // point instead of reusing the same

  public static final String EP_NUCLUES_AUTH_GLA_VERSION_LOGIN = API_GLA_VERSION_BASE_ROUTE + LOGIN;

  public static final String EP_NUCLUES_AUTH_GLA_VERSION_ANONYMOUS_LOGIN = API_GLA_VERSION_BASE_ROUTE + LOGIN + SEP + ANONYMOUS;

  public static final String EP_NUCLUES_AUTH_TOKEN = API_BASE_ROUTE + TOKEN;

  public static final String EP_NUCLUES_AUTH_AUTHORIZE = API_BASE_ROUTE + AUTHORIZE;

  public static final String EP_NUCLUES_AUTH_USER = API_BASE_ROUTE + USERS;
  public static final String EP_NUCLUES_AUTH_USER_ID = API_BASE_ROUTE + USERS + SEP + USER_ID;
  public static final String EP_NUCLUES_AUTH_USERS = API_BASE_ROUTE + USERS;
  public static final String EP_NUCLUES_AUTH_RESET_PASSWORD = API_BASE_ROUTE + USERS + SEP + RESET_PASSWORD;
  public static final String EP_NUCLUES_AUTH_PASSWORD = API_BASE_ROUTE + USERS + SEP + USER_ID + SEP + PASSWORD;
  public static final String EP_NUCLUES_AUTH_EMAIL = API_BASE_ROUTE + USERS + SEP + USER_ID + SEP + EMAIL;
  public static final String EP_NUCLUES_AUTH_EMAIL_CONFIRM = API_BASE_ROUTE + USERS + SEP + USER_ID + SEP + EMAIL_CONFIRM;

  // User preference
  public static final String EP_NUCLUES_AUTH_USER_ID_PREFS = API_BASE_ROUTE + USERS + SEP + USER_ID + SEP + USER_PREFERENCES;

  public static final String EP_NUCLUES_AUTH_GOOGLE_CONNECT_DRIVE = API_BASE_ROUTE + GOOGLE_DRIVE;

  public static final String EP_NUCLUES_AUTH_GOOGLE_DRIVE_CALLBACK = API_BASE_ROUTE + GOOGLE_DRIVE + SEP + CALLBACK;

  public static final String EP_NUCLUES_AUTH_GOOGLE_DRIVE_REFRESH_TOKEN = API_BASE_ROUTE + GOOGLE_DRIVE + SEP + REFRESH_TOKEN;

  public static final long DEFAULT_TIMEOUT = 30000L;

}
