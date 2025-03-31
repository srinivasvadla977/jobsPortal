package com.mycreation.jobsPortal.rest_controllers;

import com.mycreation.jobsPortal.dto.AuthRequest;
import com.mycreation.jobsPortal.dto.AuthResponse;
import com.mycreation.jobsPortal.model.User;
import com.mycreation.jobsPortal.services.UserService;
import com.mycreation.jobsPortal.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

@Autowired
UserService userService;

@Autowired
JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Optional<User> user = userService.authenticateUser(request.getEmail(), request.getPassword());

        if (user.isPresent()) {
            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
