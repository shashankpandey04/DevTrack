package com.shashank.devtrack.controller;

// GetMapping: Maps HTTP GET requests to a specific handler method.
import org.springframework.web.bind.annotation.GetMapping;

// RequestMapping: Defines a common URL path for all endpoints in this controller.
import org.springframework.web.bind.annotation.RequestMapping;

// RestController: Marks this class as a REST API controller.
// It combines @Controller and @ResponseBody, so return values are written
// directly to the HTTP response body, typically as JSON.
import org.springframework.web.bind.annotation.RestController;

// Map: A Java interface that stores data as key-value pairs.
// Here, both keys and values will be Strings.
import java.util.Map;

@RestController
@RequestMapping("/api/v1") // Common URL prefix for all endpoints in this controller.
public class HealthController {

    // GetMapping: Handles HTTP GET requests sent to /api/v1/health.
    @GetMapping("/health")
    public Map<String, String> health() {

        // Map.of(): Creates an immutable map containing key-value pairs.
        // Spring Boot automatically serializes this map into a JSON response.
        //
        // Expected JSON response:
        // {
        //     "status": "healthy",
        //     "app": "devtrack"
        // }
        //
        // Immutable means entries cannot be added, removed, or modified.
        return Map.of(
            "status", "healthy",
            "app", "devtrack"
        );
    }
}