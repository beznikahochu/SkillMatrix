package by.skillmatrix.service;

import by.skillmatrix.dto.auth.JwtRequest;
import by.skillmatrix.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse createAuthToken(JwtRequest jwtRequest);
}
