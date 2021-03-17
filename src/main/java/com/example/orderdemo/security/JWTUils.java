package com.example.orderdemo.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUils {
    private static final Logger logger = LoggerFactory.getLogger(JWTUils.class);
    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;
    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generJwtToken(Authentication authentication) {
        UserDetailImp userDetailImp = (UserDetailImp) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetailImp.getUser())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authenToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authenToken);
            return true;
        } catch (SignatureException e) {
            logger.error("invalid JWT signature ", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("invalid JWT token ", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("invalid JWT expired ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("invalid JWT unsport ", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("invalid JWT String is empty ", e.getMessage());
        }
        return false;
    }
}
