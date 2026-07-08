package com.tradex.stock_trading_platform.service;

import com.tradex.stock_trading_platform.dto.RegisterRequest;
import com.tradex.stock_trading_platform.entity.User;
import com.tradex.stock_trading_platform.entity.Wallet;
import com.tradex.stock_trading_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tradex.stock_trading_platform.dto.LoginRequest;


import java.time.LocalDateTime;
import java.util.Optional;

import com.tradex.stock_trading_platform.dto.AuthResponse;

import com.tradex.stock_trading_platform.repository.WalletRepository;
@Service
public class AuthService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public String register(RegisterRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
        Wallet wallet = new Wallet(100000.0, user);
        walletRepository.save(wallet);


        return "User Registered Successfully";


    }
    public AuthResponse login(LoginRequest request) {

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return new AuthResponse("User not found", null);
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid Credentials", null);
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse("Login Successful", token);
    }
}