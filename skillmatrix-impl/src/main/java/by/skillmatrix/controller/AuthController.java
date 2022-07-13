package by.skillmatrix.controller;

import by.skillmatrix.dto.auth.JwtRequest;
import by.skillmatrix.dto.auth.JwtResponse;
import by.skillmatrix.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Tag(name = "3. Auth Controller", description = "access controller for issuing tokens to users")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Authorization", description = "Get token by login and password")
    public JwtResponse createAuthTokenFromLogin(JwtRequest jwtRequest) {
        log.trace("Create token from login, by request: {}", jwtRequest.getLogin());

        JwtResponse result = authService.createAuthToken(jwtRequest);

        log.trace("Return JwtResponse from createAuthToken for email: {}", jwtRequest.getLogin());
        return result;
    }
}
