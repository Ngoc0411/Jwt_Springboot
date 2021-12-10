package com.team2.user_management.controllers;

import com.team2.user_management.payload.request.LoginRequest;
import com.team2.user_management.payload.request.SignupRequest;
import com.team2.user_management.service.AuthService;
import com.team2.user_management.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<ServiceResult> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        ServiceResult sr = authService.authenticateUser(loginRequest);
        return sr.getStatus() == ServiceResult.Status.FAILED ? new ResponseEntity<ServiceResult>(sr, HttpStatus.UNAUTHORIZED):
                new ResponseEntity<ServiceResult>(sr, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        ServiceResult sr = authService.registerUser(signUpRequest);
        return sr.getStatus() == ServiceResult.Status.FAILED ? new ResponseEntity<ServiceResult>(sr, HttpStatus.BAD_REQUEST):
                new ResponseEntity<ServiceResult>(sr, HttpStatus.OK);
    }
}
