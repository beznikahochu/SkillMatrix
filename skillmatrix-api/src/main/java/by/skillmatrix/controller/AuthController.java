package by.skillmatrix.controller;

import by.skillmatrix.dto.auth.JwtRequest;
import by.skillmatrix.dto.auth.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "3. Auth Controller", description = "access controller for issuing tokens to users")
@RequestMapping(value = "/auth")
public interface AuthController {

    @Operation(summary = "Authorization", description = "Get token by login and password")
    @PostMapping("/login")
    JwtResponse createAuthTokenFromLogin(@RequestBody JwtRequest jwtRequest);
}
