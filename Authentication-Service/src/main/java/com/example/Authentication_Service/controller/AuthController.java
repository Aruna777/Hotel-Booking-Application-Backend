package com.example.Authentication_Service.controller;


import com.example.Authentication_Service.dto.LoginRequest;
import com.example.Authentication_Service.dto.RegisterRequest;
import com.example.Authentication_Service.model.User;
import com.example.Authentication_Service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    // Register endpoint
    @PostMapping("/register")
    public Mono<ResponseEntity<String>> register (@RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest)
                .map(user -> ResponseEntity.status(201).body("User registered successfully!"))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body("Error: " + e.getMessage())));
    }

    // Login endpoint
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest loginRequest) {
         return authService.authenticateUser(loginRequest)
                 .map(jwt -> ResponseEntity.ok("Bearer " + jwt))
                 .onErrorResume(e -> Mono.just(ResponseEntity.status(401).body("Error: " + e.getMessage())));
    }

    // Token validation endpoint
    @GetMapping("/validate")
    public Mono<ResponseEntity<User>> validateToken(@RequestHeader("Authorization") String token) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        return authService.validateToken(actualToken)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(400).build()));
    }

}
