package by.skillmatrix.service;

import by.skillmatrix.dto.auth.JwtRequest;
import by.skillmatrix.dto.auth.JwtResponse;

/**
 * Service for users authorization.
 */
public interface AuthService {
    /**
     * Get token by login and password.
     *
     * @param jwtRequest request
     * @return response
     */
    JwtResponse createAuthToken(JwtRequest jwtRequest);
}
