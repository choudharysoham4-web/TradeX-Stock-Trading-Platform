package com.tradex.stock_trading_platform.controller;

import com.tradex.stock_trading_platform.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tradex.stock_trading_platform.dto.AddMoneyRequest;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/api/wallet/balance")
    public Double getBalance() {
        return walletService.getBalance();
    }
    @PostMapping("/api/wallet/add-money")
    public String addMoney(@RequestBody AddMoneyRequest request) {
        return walletService.addMoney(request.getAmount());
    }
}