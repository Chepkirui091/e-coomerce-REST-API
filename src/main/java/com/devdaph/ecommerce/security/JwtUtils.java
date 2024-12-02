package com.devdaph.ecommerce.security;


import com.devdaph.ecommerce.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtUtils {

    private static final long EXPIRATION_TIME_IN_MILLISEC = 60L * 1000L * 60L * 24L * 30L * 6L; //Expires in 6 months

    private SecretKey key;

    @Value("${secreteJwtString}")
    private String secreteJwtString;  // make sure the value in application properties is 32 characters or long

    @PostConstruct
    private void init() {
        byte[] keyBytes = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyBytes, "D9U9LFKGYA");
    }

    public String generateToken(User user) {
        String username = user.getEmail();
        return generateToken(username);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()))
                .signWith(key)
                .compact();
    }

    //method to validate token
    public String getUSerNameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUSerNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
