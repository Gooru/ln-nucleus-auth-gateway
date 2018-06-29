package org.gooru.nucleus.auth.gateway.constants;

/**
 * Created by gooru.
 */
public final class RouteConstants {

    // Misc helpers
    private static final String SEP = "/";
    private static final String COLON = ":";

    // Helper constants
    public static final String API_VERSION = "version";
    private static final String API_BASE_ROUTE = "/api/nucleus-auth/" + COLON + API_VERSION + '/';
    public static final String API_NUCLUES_AUTH_ROUTE = "/api/nucleus-auth/*";
    private static final String API_INTERNAL_BASE_ROUTE = "/api/internal/" + COLON + API_VERSION + '/';

    // Helper: Operations
    public static final String TOKEN = "token";
    public static final String AUTHORIZE = "authorize";
    private static final String USERS = "users";
    public static final String RESET_PASSWORD = "reset-password";
    private static final String CHANGE_PASSWORD = "change-password";
    private static final String GOOGLE_DRIVE = "google-drive";
    private static final String CALLBACK = "callback";
    private static final String REFRESH_TOKEN = "refresh-token";
    public static final String REDIRECT = "redirect";
    public static final String INIT_LOGIN = "initlogin";

    // Actual End Point Constants: Note that constant values may be duplicated
    // but we are going to have individual constant values to work with for each
    // point instead of reusing the same

    /*
     * NEW ROUTES
     */
    public static final String SIGNIN = "signin";
    private static final String SIGNOUT = "signout";
    public static final String SIGNUP = "signup";

    public static final String EP_NUCLEUS_USER_SIGNIN = API_BASE_ROUTE + SIGNIN;
    public static final String EP_NUCLEUS_USER_SIGNOUT = API_BASE_ROUTE + SIGNOUT;
    public static final String EP_NUCLUES_AUTH_TOKEN = API_BASE_ROUTE + TOKEN;

    public static final String EP_NUCLUES_AUTH_AUTHORIZE = API_BASE_ROUTE + AUTHORIZE;

    public static final String EP_NUCLUES_USER_SIGNUP = API_BASE_ROUTE + SIGNUP;
    public static final String EP_NUCLUES_USER_UPDATE = API_BASE_ROUTE + USERS;
    public static final String EP_NUCLUES_USER_RESET_PASSWORD = API_BASE_ROUTE + USERS + SEP + RESET_PASSWORD;
    public static final String EP_NUCLUES_USER_CHANGE_PASSWORD = API_BASE_ROUTE + USERS + SEP + CHANGE_PASSWORD;
    public static final String EP_DOMAIN_BASED_REDIRECT = API_BASE_ROUTE + REDIRECT;
    public static final String EP_INIT_LOGIN = API_BASE_ROUTE + INIT_LOGIN;

    public static final String EP_NUCLUES_AUTH_GOOGLE_CONNECT_DRIVE = API_BASE_ROUTE + GOOGLE_DRIVE;
    public static final String EP_NUCLUES_AUTH_GOOGLE_DRIVE_CALLBACK = API_BASE_ROUTE + GOOGLE_DRIVE + SEP + CALLBACK;
    public static final String EP_NUCLUES_AUTH_GOOGLE_DRIVE_REFRESH_TOKEN =
        API_BASE_ROUTE + GOOGLE_DRIVE + SEP + REFRESH_TOKEN;

    // Internal Routes
    // /api/internal/:version/user-details
    public static final String EP_INTERNAL_AUTHENTICATE = API_INTERNAL_BASE_ROUTE + "authenticate";
    // /api/internal/:version/login-as-user
    public static final String EP_INTERNAL_IMPERSONATE = API_INTERNAL_BASE_ROUTE + "impersonate";
    // /api/internal/:version/sso
    public static final String EP_INTERNAL_SSO = API_INTERNAL_BASE_ROUTE + "sso";
    // /api/internal/:version/sso/wsfed
    public static final String EP_INTERNAL_SSO_WSFED = API_INTERNAL_BASE_ROUTE + "sso/wsfed";

    public static final long DEFAULT_TIMEOUT = 30000L;

    private RouteConstants() {
        throw new AssertionError();
    }

}
