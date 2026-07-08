package com.tradex.stock_trading_platform.repository;

import com.tradex.stock_trading_platform.entity.Transaction;
import com.tradex.stock_trading_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUser(User user);
}