package com.example.api1.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtTokenUtils {
    private String secret = "984hg493gh0439rthr0429Uruj2309yh937gc763fe87t3f89723gf";
    private Duration ttl = Duration.ofMinutes(30);

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expirirationDate = new Date(now.getTime() + ttl.toMillis());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expirirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getPayload(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }
    public String getUsername(String token) {
        return getPayload(token).getSubject();
    }
}
