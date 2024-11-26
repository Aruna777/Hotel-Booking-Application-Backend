package com.example.Authentication_Service.service;


import com.example.Authentication_Service.dto.LoginRequest;
import com.example.Authentication_Service.dto.RegisterRequest;
import com.example.Authentication_Service.model.User;
import com.example.Authentication_Service.repository.UserRepository;
import com.example.Authentication_Service.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register a new user
    public Mono<User> registerUser(RegisterRequest registerRequest) {
        return userRepository.findByEmail(registerRequest.getEmail())
                .flatMap(existingUser-> Mono.<User>error(new RuntimeException("Email already exists")))
                .switchIfEmpty(
                        Mono.defer(()-> {
                            User user = new User();
                            user.setFirstName(registerRequest.getFirstName());
                            user.setLastName(registerRequest.getLastName());
                            user.setEmail(registerRequest.getEmail());
                            user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
                            return userRepository.save(user);
                        })
                );

    }

    // Authenticate user and return JWT token
    public Mono<String> authenticateUser(LoginRequest loginRequest){
        return userRepository.findByEmail(loginRequest.getEmail())
                .flatMap(user -> {
                    if(bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                    {
                        return Mono.just(jwtUtil.generateToken(user));
                    }
                    else{
                        return Mono.error(new RuntimeException("Invalid Credential"));
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));

    }

    // Validate token and return the associated user
    public Mono<User> validateToken(String token) {
        try {
            String email = jwtUtil.validateToken(token);
            return userRepository.findByEmail(email)
                    .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Token validation failed: " + e.getMessage()));
        }
    }
}
