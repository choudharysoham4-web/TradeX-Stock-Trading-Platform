package com.tradex.stock_trading_platform.dto;

import java.time.LocalDateTime;

public class TransactionResponse {

    private String type;
    private String symbol;
    private Integer quantity;
    private Double price;
    private Double totalAmount;
    private LocalDateTime transactionTime;

    public TransactionResponse(String type, String symbol,
                               Integer quantity,
                               Double price,
                               Double totalAmount,
                               LocalDateTime transactionTime) {

        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.transactionTime = transactionTime;
    }

    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }
}