package com.simplon.dieandretry.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.Authentication;

import com.simplon.dieandretry.models.AuthenticationRequest;

public interface AuthenticationService {

	Authentication getAuthentication(Jws<Claims> request);
	Authentication authenticate(AuthenticationRequest authenticationRequest);
}