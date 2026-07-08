package com.tradex.stock_trading_platform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double averageBuyPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public Portfolio() {
    }

    public Portfolio(User user, Stock stock, Integer quantity, Double averageBuyPrice) {
        this.user = user;
        this.stock = stock;
        this.quantity = quantity;
        this.averageBuyPrice = averageBuyPrice;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public User getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setAverageBuyPrice(Double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}