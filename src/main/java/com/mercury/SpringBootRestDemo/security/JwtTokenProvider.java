package com.mercury.SpringBootRestDemo.security;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(Authentication authentication, int userId) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        byte[] decodedSecret = Base64.getDecoder().decode(jwtSecret);
        SecretKey key = Keys.hmacShaKeyFor(decodedSecret);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public int getUserIdFromJWT(String token) {
        byte[] decodedSecret = Base64.getDecoder().decode(jwtSecret);
        Claims claims = Jwts.parser()
                .setSigningKey(decodedSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", int.class);
    }

    public String getUsernameFromJWT(String token) {
        byte[] decodedSecret = Base64.getDecoder().decode(jwtSecret);
        Claims claims = Jwts.parser()
                .setSigningKey(decodedSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    public boolean validateToken(String authToken) {
        try {
            byte[] decodedSecret = Base64.getDecoder().decode(jwtSecret);
            Jwts.parser().setSigningKey(decodedSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
            logger.error("Invalid JWT token", ex);
        }
        return false;
    }

}
