package by.skillmatrix.controller;

import by.skillmatrix.dto.auth.JwtRefresh;
import by.skillmatrix.dto.auth.JwtRequest;
import by.skillmatrix.dto.auth.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for users authorization.
 */

@Tag(name = "Authorization Controller", description = "access controller for issuing tokens to users")
public interface AuthController {

    @Operation(summary = "Authorization", description = "Get token by email and password")
    @PostMapping("/auth/login")
    JwtResponse createAuthTokenFromLogin(@RequestBody JwtRequest jwtRequest);

    @Operation(summary = "Authorization", description = "Get auth token by refresh token")
    @PostMapping("/auth/refresh")
    JwtResponse createAuthTokenFromRefreshToken(@RequestBody JwtRefresh jwtRefresh);
}
