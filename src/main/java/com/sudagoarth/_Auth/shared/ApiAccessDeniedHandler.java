package com.sudagoarth._Auth.shared;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;

@Component
public class ApiAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ApiResponse apiResponse = new ApiResponse(
                "Access Denied",
                403,
                null,
                "ACCESS_DENIED",
                false,
                accessDeniedException.getMessage(),
                Instant.now()
        );

        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
    }
}
