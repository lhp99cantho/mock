package com.example.service01.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Getter
@ToString
@Configuration
public class JwtToken {

    @Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Tạo ra JWT từ thông tin user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        return generateToken(claims, userDetails);
    }

    // Tạo ra JWT từ thông tin user kèm theo Claims
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        // Thời gian hiện tại
        long currentTimeMillis = System.currentTimeMillis();

        // Thời gian hết hạn của Token
        long expirationTimeMillis = currentTimeMillis + (1000 * 60 * 300); // 300 phút

        // Tạo JWT Token
        return Jwts.builder()
                .setClaims(extraClaims) // Claims (nội dung)
                .setSubject(userDetails.getUsername()) // Chủ đề (Subject)
                .setIssuedAt(new Date(currentTimeMillis)) // Thời gian phát hành (Issued At)
                .setExpiration(new Date(expirationTimeMillis)) // Thời gian hết hạn (Expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Ký JWT bằng SECRET_KEY
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Giải mã JWT Token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}