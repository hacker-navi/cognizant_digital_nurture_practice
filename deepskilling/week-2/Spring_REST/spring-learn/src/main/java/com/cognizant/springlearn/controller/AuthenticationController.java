package com.cognizant.springlearn.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.util.JwtUtil;

/**
 * REST Controller that handles authentication and JWT generation.
 *
 * Process (3 steps):
 *  Step 1: Receive the Authorization header (Basic auth: "Basic base64(user:pwd)")
 *  Step 2: Decode the Base64 header to extract username and password
 *  Step 3: Generate and return JWT token
 *
 * Sample curl command:
 *   curl -s -u user:pwd http://localhost:8083/authenticate
 *
 * Sample Response:
 *   {"token":"eyJhbGciOiJIUzI1NiJ9..."}
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Authenticates the user via Basic Auth header and returns a JWT token.
     *
     * Step 1: Read the "Authorization" header from the request.
     *         Format: "Basic <Base64(username:password)>"
     *
     * Step 2: Decode the Base64 encoded credentials.
     *         Split on ":" to extract username and password.
     *
     * Step 3: Generate JWT token using the extracted username.
     *         (In production, validate credentials against a user store here.)
     *
     * @param authHeader the HTTP Authorization header value
     * @return Map containing the generated JWT token
     */
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(
            @RequestHeader("Authorization") String authHeader) {

        LOGGER.info("Start authenticate");
        LOGGER.debug("Authorization header received");

        // Step 2: Decode Base64 encoded credentials
        // "Basic dXNlcjpwd2Q=" → remove "Basic " prefix, then decode
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);

        // Split "username:password"
        String[] parts = credentials.split(":", 2);
        String username = parts[0];
        String password = parts[1];

        LOGGER.debug("Authenticating user: {}", username);

        // Step 3: Generate JWT token for the authenticated user
        // TODO: In production, validate username/password against DB or UserDetailsService
        String token = jwtUtil.generateToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        LOGGER.info("End authenticate, token generated for user={}", username);
        return response;
    }
}
