# TradeX ER Diagram

```text
User
├── id
├── fullName
├── email
├── password
├── role
└── createdAt

Wallet
├── id
├── balance
└── user_id

Stock
├── id
├── symbol
├── companyName
├── currentPrice
├── availableQuantity
├── sector
└── exchangeName

Portfolio
├── id
├── quantity
├── averageBuyPrice
├── user_id
└── stock_id

Transaction
├── id
├── type
├── quantity
├── price
├── totalAmount
├── transactionTime
├── user_id
└── stock_id