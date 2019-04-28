package com.simplon.dieandretry.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import com.simplon.dieandretry.models.JwtTokens;

public interface JwtTokenService {

    JwtTokens createTokens(Authentication authentication);
    String createToken(User user);
    Jws<Claims> validateJwtToken(String token);
}