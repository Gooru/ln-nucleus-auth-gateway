package org.gooru.nucleus.auth.gateway.constants;

public final class MessageConstants {

    public static final String MSG_HEADER_OP = "mb.operation";
    public static final String MSG_API_VERSION = "api.version";
    public static final String MSG_HEADER_TOKEN = "access.token";
    public static final String MSG_HEADER_BASIC_AUTH = "basic.auth";
    public static final String MSG_OP_STATUS = "mb.operation.status";
    public static final String MSG_OP_STATUS_SUCCESS = "success";
    public static final String MSG_OP_STATUS_ERROR = "error";
    public static final String MSG_OP_STATUS_VALIDATION_ERROR = "error.validation";
    public static final String MSG_USER_ANONYMOUS = "anonymous";
    public static final String MSG_USER_ID = "user_id";
    public static final String MSG_USER_CONTEXT_HOLDER = "user.context.holder";
    public static final String MSG_HTTP_STATUS = "http.status";
    public static final String MSG_HTTP_BODY = "http.body";
    public static final String MSG_HTTP_RESPONSE = "http.response";
    public static final String MSG_HTTP_ERROR = "http.error";
    public static final String MSG_HTTP_VALIDATION_ERROR = "http.validation.error";
    public static final String MSG_HTTP_HEADERS = "http.headers";
    public static final String MSG_HTTP_PARAM = "http.params";
    public static final String MSG_HEADER_REQUEST_DOMAIN = "http.request.domain";
    public static final String MSG_HEADER_API_KEY = "http.api.key";
    public static final String MSG_USER_IDS = "ids";
    
    public static final String MSG_OP_ANONYMOUS_SIGNIN = "anonymous.signin";
    public static final String MSG_OP_USER_SIGNIN = "user.signin";
    public static final String MSG_OP_USER_SIGNOUT = "user.signout";
    public static final String MSG_OP_USER_TOKEN_CHECK = "user.token.check";
    public static final String MSG_OP_USER_TOKEN_DETAILS = "user.token.details";
    public static final String MSG_OP_USER_UPDATE = "user.update";
    public static final String MSG_OP_USER_GET = "user.get";
    public static final String MSG_OP_USER_PASSWORD_RESET_TRIGGER = "user.password.reset.trigger";
    public static final String MSG_OP_USER_PASSWORD_RESET = "user.password.reset";
    public static final String MSG_OP_USER_PASSWORD_CHANGE = "user.password.change";
    
    public static final String MSG_OP_USER_SIGNUP = "user.signup";
    
    public static final String MSG_OP_INTERNAL_AUTHENTICATE = "internal.authenticate";
    public static final String MSG_OP_INTERNAL_IMPERSONATE = "internal.impersonate";
    public static final String MSG_OP_INTERNAL_LTI_SSO = "internal.lti.sso";
    
    //GoogleDrive
    public static final String MSG_OP_GOOGLE_DRIVE_CONNECT = "google.drive.connect";
    public static final String MSG_OP_GOOGLE_DRIVE_CALLBACK = "google.drive.callback";
    public static final String MSG_OP_GOOGLE_DRIVE_REFRESH_TOKEN = "google.drive.refresh.token";
    public static final String MSG_OP_GOOGLE_DRIVE_DELETE_REFRESH_TOKEN = "google.drive.delete.refresh.token";

    // Authorize command
    public static final String MSG_OP_USER_AUTHORIZE = "user.authorize";
    
    private MessageConstants() {
        throw new AssertionError();
    }

}

