package hu.fulopmark.boatdatacollectorbe.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component("restAuthenticationEntryPoint")
@Slf4j
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${http.auth-token-header-name}")
    private String principalRequestHeader;

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {

        log.warn("AuthenticationException occured for resource {}, source IP {}, missing API key header? {}", request.getRequestURI(), request.getHeader("x-forwarded-for"), request.getHeader(principalRequestHeader) == null ? "yes" : "no");

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String responseJson = "{ \"timestamp\": \"" + Instant.now() + "\"," +
                "\"status\": " + HttpServletResponse.SC_UNAUTHORIZED + "," +
                "\"error\": \"Unathorized\"," +
                "\"message\": \"" + authenticationException.getMessage() + "\"," +
                "\"path\": \"" + request.getRequestURI() + "\"}";

        response.getOutputStream().println(responseJson);
        //response.getOutputStream().println("{ \"timestamp\": \"" +  \"error\": \"" + authenticationException.getMessage() + "\" }");

    }
}