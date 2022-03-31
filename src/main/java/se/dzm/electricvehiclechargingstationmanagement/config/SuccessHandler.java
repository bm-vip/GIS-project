package se.dzm.electricvehiclechargingstationmanagement.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static se.dzm.electricvehiclechargingstationmanagement.enums.RoleType.*;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecurityContextHolderAwareRequestWrapper requestWrapper = new SecurityContextHolderAwareRequestWrapper(request, "");
        String targetUrl = "/access-denied";
        if (requestWrapper.isUserInRole(ADMIN) || requestWrapper.isUserInRole(SUPER_WISER))
            targetUrl = "/dashboard";
        else if (requestWrapper.isUserInRole(USER) || requestWrapper.isUserInRole(GUEST))
            targetUrl = "/company";
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
