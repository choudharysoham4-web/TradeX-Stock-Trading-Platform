package com.tradex.stock_trading_platform.service;

import com.tradex.stock_trading_platform.dto.StockRequest;
import com.tradex.stock_trading_platform.entity.Stock;
import com.tradex.stock_trading_platform.exception.StockNotFoundException;
import com.tradex.stock_trading_platform.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradex.stock_trading_platform.dto.BuyStockRequest;
import com.tradex.stock_trading_platform.entity.Portfolio;
import com.tradex.stock_trading_platform.entity.User;
import com.tradex.stock_trading_platform.entity.Wallet;
import com.tradex.stock_trading_platform.repository.PortfolioRepository;
import com.tradex.stock_trading_platform.repository.UserRepository;
import com.tradex.stock_trading_platform.repository.WalletRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.tradex.stock_trading_platform.dto.PortfolioResponse;
import java.util.List;
import java.util.stream.Collectors;
import com.tradex.stock_trading_platform.dto.SellStockRequest;
import com.tradex.stock_trading_platform.entity.Transaction;
import com.tradex.stock_trading_platform.repository.TransactionRepository;
import java.time.LocalDateTime;
import com.tradex.stock_trading_platform.dto.TransactionResponse;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class StockService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    public String addStock(StockRequest request) {

        if (stockRepository.findBySymbol(request.getSymbol()).isPresent()) {
            throw new RuntimeException("Stock already exists");
        }

        Stock stock = new Stock(
                request.getSymbol(),
                request.getCompanyName(),
                request.getCurrentPrice(),
                request.getAvailableQuantity(),
                request.getSector(),
                request.getExchangeName()
        );

        stockRepository.save(stock);

        return "Stock added successfully!";
    }
    public String buyStock(BuyStockRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        Stock stock = stockRepository.findBySymbol(request.getSymbol())
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        if (stock.getAvailableQuantity() < request.getQuantity()) {
            return "Not enough stock quantity available";
        }

        double totalCost = stock.getCurrentPrice() * request.getQuantity();

        if (wallet.getBalance() < totalCost) {
            return "Insufficient wallet balance";
        }

        wallet.setBalance(wallet.getBalance() - totalCost);

        stock.setAvailableQuantity(stock.getAvailableQuantity() - request.getQuantity());

        Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock)
                .orElse(new Portfolio(user, stock, 0, stock.getCurrentPrice()));

        int oldQuantity = portfolio.getQuantity();
        double oldAvgPrice = portfolio.getAverageBuyPrice();

        int newQuantity = oldQuantity + request.getQuantity();

        double newAvgPrice =
                ((oldQuantity * oldAvgPrice) + totalCost) / newQuantity;

        portfolio.setQuantity(newQuantity);
        portfolio.setAverageBuyPrice(newAvgPrice);

        walletRepository.save(wallet);
        stockRepository.save(stock);
        portfolioRepository.save(portfolio);

        Transaction transaction = new Transaction(
                "BUY",
                request.getQuantity(),
                stock.getCurrentPrice(),
                totalCost,
                LocalDateTime.now(),
                user,
                stock
        );

        transactionRepository.save(transaction);

        return "Stock bought successfully!";
    }
    public List<PortfolioResponse> getPortfolio() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Portfolio> portfolios = portfolioRepository.findByUser(user);

        return portfolios.stream()
                .map(portfolio -> {

                    Double totalValue =
                            portfolio.getQuantity() * portfolio.getStock().getCurrentPrice();

                    Double profitLoss =
                            (portfolio.getStock().getCurrentPrice()
                                    - portfolio.getAverageBuyPrice())
                                    * portfolio.getQuantity();

                    return new PortfolioResponse(
                            portfolio.getStock().getSymbol(),
                            portfolio.getStock().getCompanyName(),
                            portfolio.getQuantity(),
                            portfolio.getAverageBuyPrice(),
                            portfolio.getStock().getCurrentPrice(),
                            totalValue,
                            profitLoss
                    );

                })
                .collect(Collectors.toList());
    }
    public String sellStock(SellStockRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        Stock stock = stockRepository.findBySymbol(request.getSymbol())
                .orElseThrow(() -> new StockNotFoundException("Stock not found"));

        Portfolio portfolio = portfolioRepository.findByUserAndStock(user, stock)
                .orElseThrow(() -> new RuntimeException("Stock not found in portfolio"));

        if (portfolio.getQuantity() < request.getQuantity()) {
            return "You don't have enough shares to sell.";
        }

        double totalAmount = stock.getCurrentPrice() * request.getQuantity();

        // Add money back to wallet
        wallet.setBalance(wallet.getBalance() + totalAmount);

        // Increase available stock quantity
        stock.setAvailableQuantity(
                stock.getAvailableQuantity() + request.getQuantity()
        );

        // Reduce portfolio quantity
        portfolio.setQuantity(
                portfolio.getQuantity() - request.getQuantity()
        );

        walletRepository.save(wallet);
        stockRepository.save(stock);

        if (portfolio.getQuantity() == 0) {
            portfolioRepository.delete(portfolio);
        } else {
            portfolioRepository.save(portfolio);
        }

        Transaction transaction = new Transaction(
                "SELL",
                request.getQuantity(),
                stock.getCurrentPrice(),
                totalAmount,
                LocalDateTime.now(),
                user,
                stock
        );

        transactionRepository.save(transaction);

        return "Stock sold successfully!";
    }
    public List<TransactionResponse> getTransactions() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Transaction> transactions = transactionRepository.findByUser(user);

        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getType(),
                        transaction.getStock().getSymbol(),
                        transaction.getQuantity(),
                        transaction.getPrice(),
                        transaction.getTotalAmount(),
                        transaction.getTransactionTime()
                ))
                .collect(Collectors.toList());
    }

}