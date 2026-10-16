package com.example.SOCApplication.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
//JWT utility class
public class JWTUtil {

    //Expiration time of Refresh Token
    private final Integer REFRESH_TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;

    //Expiration time of Access Token
    private final Integer ACCESS_TOKEN_EXPIRATION_TIME = 20 * 1000;

    //Initialising the key
    private final String key = "a-string-secret-at-least-256-bits-long-generateJWT";

    //The key that is used to sign the key
    private final SecretKey keys = Keys.hmacShaKeyFor(key.getBytes());

    //Generate an Access Token
    public String GenerateAccessToken(String username){
        return Jwts.builder()
                .issuedAt(new Date())
                .subject(username)
                .signWith(keys)
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .compact();
    }

    public Date getExpirationDateFromToken(String token){
        return extractClaims(token).getExpiration();
    }

    //Generate a Refresh Token
    public String GenerateRefreshToken(String username){
        return Jwts.builder()
                .issuedAt(new Date())
                .subject(username)
                .signWith(keys)
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();

    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(keys)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, String username, UserDetails userDetails) {
        return userDetails.getUsername().equals(username) && !checkExpiration(token);
    }

    public boolean checkExpiration(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
}
