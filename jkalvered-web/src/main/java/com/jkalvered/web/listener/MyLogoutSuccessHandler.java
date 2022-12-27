package com.jkalvered.web.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
        }
        LOGGER.info("Je suis passé invalider tout ca");

        response.sendRedirect("/niddah-web/public/index.do?logSucc=true");
    }
}
