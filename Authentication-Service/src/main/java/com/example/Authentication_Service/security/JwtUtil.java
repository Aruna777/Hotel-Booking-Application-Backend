package com.example.Authentication_Service.security;

import com.example.Authentication_Service.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;


    private static final long EXPIRATION_TIME = 3600000;

    public String generateToken(User user) {

        byte[] keyBytes = secretKey.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String validateToken(String token) {
        try {
            byte[] keyBytes = secretKey.getBytes();
            SecretKey key = Keys.hmacShaKeyFor(keyBytes);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token has expired", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Invalid JWT token", e);
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid token signature", e);
        } catch (Exception e) {
            throw new RuntimeException("Token validation failed", e);
        }
    }

    public Claims extractClaims(String token) {
        byte[] keyBytes = secretKey.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
