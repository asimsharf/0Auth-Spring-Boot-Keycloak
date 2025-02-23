package com.sudagoarth._Auth.shared;

import java.time.Instant;

public record ApiResponse(
        String message,
        int status,
        Object data,
        String code,
        boolean success,
        String errorDetails,
        Instant timestamp) {

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(message, 200, data, "OK", true, null, Instant.now());
    }

    public static ApiResponse error(String message, String code, String errorDetails) {
        return new ApiResponse(message, 500, null, code, false, errorDetails, Instant.now());
    }
}
