package spring_security.security.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorCustomHandler implements ErrorController {
     @RequestMapping("/error")
     public String handleError(HttpServletRequest request) {
          Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
          Integer statusCode = Integer.valueOf(status.toString());
          Integer code = HttpStatus.FORBIDDEN.value();
          if (status != null) {
               // Integer statusCode = Integer.valueOf(status.toString());
          
               if(statusCode == HttpStatus.NOT_FOUND.value()) {

                    System.out.println("Error with code " + statusCode +" | "+ code+ " Happened!");
                    return "404";
               }
               else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

                    System.out.println("Error with code " + statusCode + " Happened!");
                    return "500";
               }
                else if(statusCode == HttpStatus.FORBIDDEN.value()) {

                    System.out.println("Error with code " + statusCode + " Happened!");
                    return "403";
               }
          }
          System.out.println("Outer Error with code " + statusCode + " Happened!");

          return "error";
     }
     
}
