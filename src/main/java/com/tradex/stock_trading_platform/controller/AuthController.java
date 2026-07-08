package com.tradex.stock_trading_platform.controller;

import com.tradex.stock_trading_platform.dto.RegisterRequest;
import com.tradex.stock_trading_platform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tradex.stock_trading_platform.dto.LoginRequest;
import com.tradex.stock_trading_platform.dto.AuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}