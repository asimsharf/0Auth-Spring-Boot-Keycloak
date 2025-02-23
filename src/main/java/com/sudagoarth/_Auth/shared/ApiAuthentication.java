package com.sudagoarth._Auth.shared;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;

@Component
public class ApiAuthentication implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // Check if the error is related to JWT authentication failure
        if (authException.getMessage().contains("JWT")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            ApiResponse apiResponse = new ApiResponse(
                    "Unauthorized access. Please check your authentication token.",
                    401,
                    null,
                    "UNAUTHORIZED",
                    false,
                    "JWT authentication failed",
                    Instant.now()
            );

            response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        } else {
            // Default behavior for other authentication failures
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");

            ApiResponse apiResponse = new ApiResponse(
                    "Unauthorized access. Please check your authentication token.",
                    401,
                    null,
                    "UNAUTHORIZED",
                    false,
                    authException.getMessage(),
                    Instant.now()
            );

            response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        }
    }
}
