# TradeX Architecture

```
                    Client (Postman / Frontend)
                              │
                              ▼
                     Spring Security Filter
                              │
                              ▼
                  JwtAuthenticationFilter
                              │
                              ▼
                      Controller Layer
                              │
                              ▼
                       Service Layer
                              │
                              ▼
                    Repository Layer (JPA)
                              │
                              ▼
                          MySQL Database
```

---

## Flow

```
Client Request
      │
      ▼
JWT Validation
      │
      ▼
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
Database
      │
      ▼
Response
```

---

## Modules

- Authentication
- Wallet
- Stock
- Portfolio
- Transactions
