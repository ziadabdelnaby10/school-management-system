package com.ziad.school.service;

import com.ziad.school.utils.SchoolConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Getter
    @Value("${security.jwt.expiration-time}")
    private long expiration;

    private Claims extractAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String buildToken(
            String email,
            Collection<? extends GrantedAuthority> authorities
    ) {
        return Jwts.builder()
                .setIssuer(SchoolConstants.JWT_ISSUER)
                .setSubject(email)
                .claim(SchoolConstants.CLAIM_EMAIL, email)
                .claim(SchoolConstants.CLAIM_AUTHORITIES, authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    public boolean isTokenValid(String token, String checkedEmail) {
        final String email = extractEmail(token);
        return (email.equals(checkedEmail)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) {
        return String.valueOf(extractAllClaimsFromToken(token).get(SchoolConstants.CLAIM_EMAIL));
    }

    public String extractAuthorities(String token) {
        return String.valueOf(extractAllClaimsFromToken(token).get(SchoolConstants.CLAIM_AUTHORITIES));
    }

    public String generateToken(String email,
                                Collection<? extends GrantedAuthority> authorities) {
        return buildToken(email , authorities);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
