package by.skillmatrix.controller;

import by.skillmatrix.dto.auth.JwtRefresh;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for users refresh tokens.
 */
@Tag(name = "Authorization Refresh Controller", description = "access controller for issuing refresh tokens to users")
public interface AuthRefreshController {
    @Operation(summary = "Refresh", description = "Get refresh token by auth token")
    @PostMapping("auth/refresh")
    JwtRefresh createRefreshToken();
}
