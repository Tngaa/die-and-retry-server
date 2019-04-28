package com.simplon.dieandretry.services.impl;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.simplon.dieandretry.models.JwtTokens;
import com.simplon.dieandretry.services.JwtTokenService;

import java.util.Calendar;
import java.util.Date;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${token.secret}")
    private String secret;

    @Override
    public JwtTokens createTokens(Authentication authentication) {

        String token;

        User user = (User) authentication.getPrincipal();

        token = createToken(user);

        return new JwtTokens(token);
    }

    @Override
    public String createToken(User user) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(getTokenExpirationDate(false))
                .setIssuedAt(new Date())
                .compact();
    }

    @Override
    public Jws<Claims> validateJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }

    private Date getTokenExpirationDate(boolean refreshToken) {
        Calendar calendar = Calendar.getInstance();

        if(refreshToken) {
            calendar.add(Calendar.MONTH, 1);
        } else {
            calendar.add(Calendar.MINUTE, 5);
        }

        return calendar.getTime();
    }
}