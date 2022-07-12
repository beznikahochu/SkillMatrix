package by.skillmatrix.service.impl;

import by.skillmatrix.dto.auth.JwtRequest;
import by.skillmatrix.dto.auth.JwtResponse;
import by.skillmatrix.exception.AuthenticationException;
import by.skillmatrix.impl.security.JwtTokenUtil;
import by.skillmatrix.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse createAuthToken(JwtRequest jwtRequest) {
        log.info("Create token from login, by request");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getLogin(), jwtRequest.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateAuthToken(userDetails);
            log.info("Return token from request login: {}", jwtRequest.getLogin());
            return new JwtResponse(token);

        } catch (BadCredentialsException exception) {
            log.error("Create Auth Token UNAUTHORIZED AuthenticationException:", exception);
            throw new AuthenticationException(AuthenticationException.UNAUTHORIZED);
        }
    }
}
