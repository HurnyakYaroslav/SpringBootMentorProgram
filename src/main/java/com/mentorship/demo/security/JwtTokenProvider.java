package com.mentorship.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private long expired;


    @Autowired
    private JwtAbiturientService jwtAbiturientService;

    @PostConstruct
    public void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String firstName) {
//    public String createToken(String firstName, List<Role> claimList) {
        Claims claims = Jwts.claims().setSubject(firstName);
//        claims.put("roles", claimList.stream().map(Role::getName).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + expired);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        JwtAbiturient abiturient = (JwtAbiturient) jwtAbiturientService.loadUserByUsername(getFirstName(token));
        return new UsernamePasswordAuthenticationToken(abiturient, "", abiturient.getAuthorities());
    }

    public boolean validateToken(String token) throws AuthException {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claimsJws.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException | JwtException e) {
            throw new AuthException("Jwt token is expired or invalid");
        }
    }

    public String getFirstName(String token) throws JwtException {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
