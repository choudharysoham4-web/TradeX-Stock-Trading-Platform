package com.tradex.stock_trading_platform.repository;

import com.tradex.stock_trading_platform.entity.User;
import com.tradex.stock_trading_platform.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(User user);

}