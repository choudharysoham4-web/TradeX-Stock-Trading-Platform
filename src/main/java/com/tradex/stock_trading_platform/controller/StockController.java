package com.tradex.stock_trading_platform.controller;

import com.tradex.stock_trading_platform.dto.*;
import com.tradex.stock_trading_platform.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tradex.stock_trading_platform.dto.ApiResponse;
import java.util.List;


@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public String addStock(@RequestBody StockRequest request) {
        return stockService.addStock(request);
    }
    @PostMapping("/buy")
    public ResponseEntity<ApiResponse> buyStock(@Valid @RequestBody BuyStockRequest request) {

        String message = stockService.buyStock(request);

        return ResponseEntity.ok(
                new ApiResponse(true, message)
        );
    }
    @GetMapping("/portfolio")
    public List<PortfolioResponse> getPortfolio() {
        return stockService.getPortfolio();
    }
    @PostMapping("/sell")
    public String sellStock(@RequestBody SellStockRequest request) {
        return stockService.sellStock(request);
    }
    @GetMapping("/transactions")
    public List<TransactionResponse> getTransactions() {
        return stockService.getTransactions();
    }
}