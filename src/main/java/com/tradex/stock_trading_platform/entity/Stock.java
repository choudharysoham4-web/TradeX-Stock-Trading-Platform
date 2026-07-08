package com.tradex.stock_trading_platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String symbol;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Double currentPrice;

    @Column(nullable = false)
    private Integer availableQuantity;

    private String sector;

    private String exchangeName;

    public Stock() {
    }

    public Stock(String symbol, String companyName, Double currentPrice, Integer availableQuantity, String sector, String exchangeName) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.availableQuantity = availableQuantity;
        this.sector = sector;
        this.exchangeName = exchangeName;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public String getSector() {
        return sector;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }
}