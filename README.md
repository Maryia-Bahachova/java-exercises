# ☕ Coffee Machine Middleware

Design Patterns Showcase Project  
Language: Java

---

## 📌 Overview

This project implements a middleware layer between an **OrderDesk** (front-end)
and a **CoffeeMachine** interface.

The goal is to demonstrate how classic **design patterns** can be combined
to build a flexible, extensible, and maintainable system that simulates
real-world device integration.

---

## 🎯 Project Goals

The middleware is responsible for:

- parsing textual coffee orders
- building region-specific coffee recipes
- supporting toppings and discounts
- controlling communication with a coffee machine
- processing orders through a clear pipeline
- reacting to order events without tight coupling

Backward compatibility with the old connector is fully preserved.

---

## 🧱 Architecture

```
OrderDesk
   ↓
Order Processing Pipeline
   ↓
CoffeeMachineController
   ↓
CoffeeMachineConnector (Old / New)
   ↓
Coffee Machine
```

---

## ☕ Supported Coffee Types

Each coffee defines:

- Water (ml)
- Coffee (g)
- Milk (ml)

Command format sent to the machine:

```
<water>ml <coffee>g <milk>ml [toppings]
```

---

## 🌍 Regional Recipes (Abstract Factory)

| Region | Espresso | Cappuccino | Latte | Notes |
|------|---------|------------|-------|------|
| Italy 🇮🇹 | 50ml / 18g / 0ml | 200ml / 15g / 100ml | 250ml / 15g / 200ml | Strong & classic |
| Lithuania 🇱🇹 | 60ml / 16g / 0ml | 200ml / 15g / 120ml | 240ml / 15g / 220ml | Balanced flavor |

Region-specific factories create coffee objects with different ingredient values.

---

## 🧁 Toppings Support (Decorator Pattern)

Supported toppings:

- Caramel — €0.50
- Cream — €0.40
- Liquor — €0.80

Features:

- toppings can be combined
- each topping affects price and machine command
- base coffee classes remain unchanged

Example order:

```
latte cream caramel
```

---

## 🔌 Coffee Machine Connectors (Adapter Pattern)

### Old Connector
- simple `makeCoffee(command)`
- works exactly as before
- fully compatible with new features

### NewCoffeeMachineConnector

Lifecycle:

```
getToken()
openSession(token)
makeCoffee(token, session, "200ml 15g 80ml caramel")
closeSession(token, session)
```

Rules:

- only one active session allowed
- strict call order validation
- simulates real external device integration

---

## 💶 Price Calculation (Strategy Pattern)

### Base Coffee Prices

| Region | Espresso | Cappuccino | Latte |
|------|---------|------------|-------|
| Italy 🇮🇹 | €2.00 | €3.50 | €4.00 |
| Lithuania 🇱🇹 | €1.80 | €3.20 | €3.80 |

### Discount Strategies

Only **one discount** may be applied per order:

- None — no discount
- Student 🎓 — 20% off
- Loyalty Card 💳 — 10% off

Example:

```
student latte cream caramel
none espresso
```

---

## 🔗 Order Processing Pipeline (Chain of Responsibility)

Order processing is divided into sequential steps:

1. Parse input
2. Identify coffee type
3. Apply toppings
4. Apply discount strategy
5. Calculate final price
6. Send command to coffee machine

Each step has exactly **one responsibility**.

---

## 🔄 Coffee Machine States (State Pattern)

The connector simulates unstable behavior using three states:

### OPEN
- normal operation
- after 2 failures → CLOSED

### CLOSED
- ignores next 5 calls
- calls do not reach the machine
- switches to SEMI-CLOSED

### SEMI-CLOSED
- allows exactly one request
- success → OPEN
- failure → CLOSED

---

## 🔔 Order Events (Observer Pattern)

The system emits events on order completion or failure.

### Listeners

- **OrderHistoryListener**  
  Stores all processed orders (successful and failed)

- **NotificationListener**  
  Outputs user notifications to the console

---

## 🧾 Order History Processing (Visitor Pattern)

New behaviors are added without modifying order or history classes.

### Visitors

#### StatisticsVisitor
- total orders
- successful vs failed
- discount usage
- total revenue
- average order price

#### ReportVisitor
- generates human-readable reports

Example output:

```
Orders processed: 42
Successful: 38
Failed: 4

Topping usage:
Caramel: 12
Cream: 4
Liquor: 6
```

---

## ✅ Quality Gate Status

✔ Clean architecture  
✔ SOLID principles  
✔ Backward compatibility  
✔ Extensible design  
✔ Real-world simulation  
✔ Design patterns used correctly

---

☕ Built for learning, clarity, and clean design.
