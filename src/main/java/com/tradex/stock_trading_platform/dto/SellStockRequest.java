package com.tradex.stock_trading_platform.dto;

public class SellStockRequest {

    private String symbol;
    private Integer quantity;

    public String getSymbol() {
        return symbol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}