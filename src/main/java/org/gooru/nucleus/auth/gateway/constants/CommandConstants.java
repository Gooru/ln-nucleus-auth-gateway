package org.gooru.nucleus.auth.gateway.constants;

public class CommandConstants {
    // Authentication command
    public static final String ANONYMOUS_CREATE_ACCESS_TOKEN = "anonymous.create.access.token";
    public static final String CREATE_ACCESS_TOKEN = "create.access.token";
    public static final String DELETE_ACCESS_TOKEN = "delete.access.token";
    public static final String GET_ACCESS_TOKEN = "get.access.token";
    public static final String CONNECT_GOOGLE_DRIVE = "google.drive.connect";
    public static final String GOOGLE_DRIVE_CALLBACK = "google.drive.callback";
    public static final String GOOGLE_DRIVE_REFRESH_TOKEN = "google.drive.refresh.token";
    public static final String GOOGLE_DRIVE_DELETE_REFRESH_TOKEN = "google.drive.delete.refresh.token";

    // Authorize command
    public static final String AUTHORIZE = "authorize";

    // User command
    public static final String CREATE_USER = "create.user";
    public static final String UPDATE_USER = "update.user";
    public static final String GET_USER = "get.user";
    public static final String UPDATE_USER_PREFERENCE = "update.user.preference";
    public static final String GET_USER_PREFERENCE = "get.user.preference";
    public static final String GET_USER_FIND = "get.user.find";
    public static final String GET_USERS_FIND = "get.users.find";
    public static final String RESET_PASSWORD = "reset.password";
    public static final String UPDATE_PASSWORD = "update.password";
    public static final String RESET_EMAIL_ADDRESS = "reset.email";
    public static final String RESEND_CONFIRMATION_EMAIL = "resend.confirmation.email";
    public static final String CONFIRMATION_EMAIL = "confirm.email";
    
    public static final String INTERNAL_AUTHENTICATE = "internal.authenticate";
    public static final String INTERNAL_IMPERSONATE = "internal.impersonate";
    
    // Auth client command
    public static final String CREATE_AUTH_CLIENT = "create.auth.client";

}
