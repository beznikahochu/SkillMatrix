package by.skillmatrix.impl.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.hours}")
    private int authHours;

    @Value("roles")
    private String roles;

    public String generateAuthToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put(roles, roleList);

        return generateToken(claims, userDetails.getUsername(), authHours);
    }

    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }


    public List<String> getRoles(String token) {
        return getClaimFromToken(token, (Function<Claims, List<String>>) claims -> claims.get(roles, List.class));
    }

    private String generateToken(Map<String, Object> claims, String userName, int hours) {
        Date issueDate = new Date();
        Date expireDate = new Date(issueDate.getTime() + 1000L * 60 * 60 * hours);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
