package by.skillmatrix.impl.controller;

import by.skillmatrix.controller.AuthController;
import by.skillmatrix.dto.auth.JwtRequest;
import by.skillmatrix.dto.auth.JwtResponse;
import by.skillmatrix.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public JwtResponse createAuthTokenFromLogin(JwtRequest jwtRequest) {
        log.trace("Create token from login, by request: {}", jwtRequest.getLogin());

        JwtResponse result = authService.createAuthToken(jwtRequest);

        log.trace("Return JwtResponse from createAuthToken for email: {}", jwtRequest.getLogin());
        return result;
    }
}
