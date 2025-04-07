package com.packt.productapi.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
    public static final String X_CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Generate or retrieve a unique correlation ID
        String correlationId = request.getHeader(X_CORRELATION_ID_HEADER);

        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put("correlationId", correlationId);

        // Capture the request start time
        long startTime = System.currentTimeMillis();

        try {
            // Proceed with the request
            filterChain.doFilter(request, response);

        } finally {
            // Capture the request end time
            long duration = System.currentTimeMillis() - startTime;

            // Log the structured request information
            logRequestDetails(request, response, duration);

            // Clean up MDC after the request
            MDC.clear();
        }
    }

    private void logRequestDetails(HttpServletRequest request, HttpServletResponse response, long duration) {
        // Extracting useful metadata for structured logging
        String httpMethod = request.getMethod();
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
        String correlationId = MDC.get("correlationId");
        int statusCode = response.getStatus();

        // Structured JSON log example
        try {
            String logEntryJson = new ObjectMapper().writeValueAsString(
                    createLogEntry(correlationId, httpMethod, requestUri + queryString, statusCode, duration)
            );
            logger.info(logEntryJson);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert log entry to JSON", e);
        }
    }

    private Map<String, Object> createLogEntry(
            String correlationId, String method, String url, int status, long duration) {
        Map<String, Object> logEntry = new HashMap<>();
        logEntry.put("timestamp", Instant.now().toString());
        logEntry.put("level", "INFO");
        logEntry.put("correlationId", correlationId);
        logEntry.put("method", method);
        logEntry.put("url", url);
        logEntry.put("status", status);
        logEntry.put("duration", duration + "ms");

        return logEntry;
    }
}

