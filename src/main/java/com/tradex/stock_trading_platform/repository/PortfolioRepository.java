package com.tradex.stock_trading_platform.repository;

import com.tradex.stock_trading_platform.entity.Portfolio;
import com.tradex.stock_trading_platform.entity.Stock;
import com.tradex.stock_trading_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByUser(User user);

    Optional<Portfolio> findByUserAndStock(User user, Stock stock);
}