package org.gooru.nucleus.auth.gateway.routes.utils;

import org.gooru.nucleus.auth.gateway.constants.RouteConstants;

import io.netty.handler.codec.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

/**
 * @author szgooru
 * Created On: 03-Jan-2017
 */
public final class RouteAuthUtility {

    private RouteAuthUtility() {
        throw new AssertionError();
    }
    
    public static boolean isAuthCheckRequired(HttpServerRequest request) {
        if (!((request.method().name().equalsIgnoreCase(HttpMethod.POST.name())) && (request.uri().endsWith(RouteConstants.AUTHORIZE)
            || request.uri().endsWith(RouteConstants.TOKEN) || request.uri().endsWith(RouteConstants.SIGNIN)))
            && !(request.method().name().equalsIgnoreCase(HttpMethod.GET.name()) && request.uri().contains(
                RouteConstants.EP_NUCLUES_AUTH_GOOGLE_DRIVE_CALLBACK))) {
            return true;
        }
        return false;
    }
}
