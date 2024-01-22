package spring_security.security.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomLogoutHandler implements LogoutSuccessHandler{

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    

     @Override
     public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
               Authentication authentication) throws IOException, ServletException {
           // TODO Auto-generated method stub
          logger.info("Logout Successfully with Principal: " + authentication.getName());
          response.setStatus(HttpServletResponse.SC_OK);
          response.sendRedirect("/signin");
     }
     
}
