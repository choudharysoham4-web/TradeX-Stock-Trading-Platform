package com.tradex.stock_trading_platform.dto;

import java.util.List;
import java.util.stream.Collector;

public class PortfolioResponse {

    private String symbol;
    private String companyName;
    private Integer quantity;
    private Double averageBuyPrice;
    private Double currentPrice;
    private Double totalValue;
    private Double profitLoss;

    public PortfolioResponse(String symbol, String companyName, Integer quantity,
                             Double averageBuyPrice, Double currentPrice, Double totalValue,Double profitLoss) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.quantity = quantity;
        this.averageBuyPrice = averageBuyPrice;
        this.currentPrice = currentPrice;
        this.totalValue = totalValue;
        this.profitLoss = profitLoss;
    }

    public PortfolioResponse(String symbol, String companyName, Integer quantity, Double averageBuyPrice, Double currentPrice, double totalValue) {
    }


    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public Double getTotalValue() {
        return totalValue;
    }
    public Double getProfitLoss() {
    return profitLoss;
}



}