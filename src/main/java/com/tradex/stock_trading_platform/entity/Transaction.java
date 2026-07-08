package com.tradex.stock_trading_platform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // BUY or SELL

    private Integer quantity;

    private Double price;

    private Double totalAmount;

    private LocalDateTime transactionTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public Transaction() {
    }

    public Transaction(String type, Integer quantity, Double price, Double totalAmount,
                       LocalDateTime transactionTime, User user, Stock stock) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.transactionTime = transactionTime;
        this.user = user;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
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

    public User getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}