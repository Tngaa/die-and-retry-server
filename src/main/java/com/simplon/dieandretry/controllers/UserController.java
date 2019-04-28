package com.simplon.dieandretry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.dieandretry.models.AuthenticationRequest;
import com.simplon.dieandretry.models.JwtTokens;
import com.simplon.dieandretry.services.JwtTokenService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenService jwtTokenService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(authentication != null && authentication.isAuthenticated()) {
            JwtTokens tokens = jwtTokenService.createTokens(authentication);
            return ResponseEntity.ok().body(tokens);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}