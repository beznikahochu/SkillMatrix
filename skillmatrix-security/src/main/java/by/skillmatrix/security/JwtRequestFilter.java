package by.skillmatrix.security;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            @NonNull HttpServletResponse httpServletResponse,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader("Authorization");

        String jwt = null;
        String userName = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                jwt = authHeader.substring(7);
                userName = jwtTokenUtil.getSubjectFromToken(jwt);
            } catch (JwtException exception) {
                new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userName, null,
                    jwtTokenUtil.getRoles(jwt).stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
