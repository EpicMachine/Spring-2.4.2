package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        for (String role : roles) {
            System.out.println(role);
        }
        if (roles.contains("admin")) {
            System.out.println("yep it's work");
            httpServletResponse.sendRedirect("/admin/");
        } else if (roles.contains("user")) {
            httpServletResponse.sendRedirect("/user/");
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }
}