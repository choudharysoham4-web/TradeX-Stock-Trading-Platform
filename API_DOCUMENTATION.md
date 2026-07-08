# 📘 TradeX API Documentation

---

# Authentication APIs

## Register User

**POST**

```
/api/auth/register
```

Request

```json
{
  "fullName": "Soham",
  "email": "soham@example.com",
  "password": "123456"
}
```

---

## Login

**POST**

```
/api/auth/login
```

Request

```json
{
  "email": "soham@example.com",
  "password": "123456"
}
```

Returns JWT Token.

---

# Wallet APIs

## Add Money

**POST**

```
/api/wallet/add-money
```

```json
{
  "amount": 5000
}
```

---

## Wallet Balance

**GET**

```
/api/wallet/balance
```

---

# Stock APIs

## Add Stock

**POST**

```
/api/stocks
```

```json
{
  "symbol":"TCS",
  "companyName":"Tata Consultancy Services",
  "currentPrice":3800,
  "availableQuantity":5000,
  "sector":"IT",
  "exchangeName":"NSE"
}
```

---

## Buy Stock

**POST**

```
/api/stocks/buy
```

```json
{
  "symbol":"TCS",
  "quantity":2
}
```

---

## Sell Stock

**POST**

```
/api/stocks/sell
```

```json
{
  "symbol":"TCS",
  "quantity":1
}
```

---

## Portfolio

**GET**

```
/api/stocks/portfolio
```

---

## Transaction History

**GET**

```
/api/stocks/transactions
```

---

# Authentication

All protected APIs require:

```
Authorization: Bearer <JWT_TOKEN>
```
