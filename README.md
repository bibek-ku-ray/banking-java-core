# Banking CLI System

A command-line banking application built in Java that allows customers to manage their accounts with core banking
operations.

## Features

### Core Banking Operations

- **Open Account**: Create a new customer account with customizable account types
- **Deposit**: Add money to an account
- **Withdraw**: Withdraw money from an account (with balance validation)
- **Transfer**: Transfer funds between two accounts
- **Account Statement**: View all transactions for a specific account
- **List Accounts**: View all accounts in the system
- **Search Accounts**: Find accounts by customer name

## Java & OOP Concepts Used

### 1. **Object-Oriented Programming (OOP)**

#### Encapsulation

- Private variables in domain classes (Account, Customer, Transaction) with public getter/setter methods
- Protects sensitive data and controls access to object properties
- Example: `Account` class with private `balance`, `accountNumber`, and public methods to access them

#### Abstraction

- `BankService` interface defines banking operations without exposing implementation details
- Clients interact with the interface, not the concrete implementation
- Hides complex logic from the user

#### Inheritance & Polymorphism

- `BankServiceImpl` implements `BankService` interface
- Allows different implementations to be swapped without changing client code

#### Single Responsibility Principle

- Each class has one clear purpose:
    - **Domain classes**: Represent data (Account, Customer, Transaction)
    - **Service classes**: Handle business logic
    - **Repository classes**: Manage data persistence
    - **Util classes**: Provide helper functions

### 2. **Design Patterns**

#### Repository Pattern

- `AccountRepository`, `CustomerRepository`, `TransactionRepository` separate data access logic
- Makes code testable and maintainable
- Allows easy switching between different storage mechanisms

#### Service Pattern

- `BankService` interface with `BankServiceImpl` implementation
- Centralizes business logic away from UI (Main.java)

### 3. **Java Features Used**

#### Enumerations (Enum)

- `Type` enum for transaction types (DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT)
- Type-safe way to represent fixed set of constants

#### Exception Handling

- Custom exceptions: `AccountNotFoundException`, `InsufficientBalanceException`, `ValidationException`
- Provides meaningful error messages to users
- Allows graceful error handling

#### Collections

- `List<Account>` and `List<Transaction>` for storing multiple objects
- Dynamic data structure for flexible data management

#### Java 8+ Features

- String templates (text blocks) for menu display
- Lambda expressions in switch statements for cleaner code

#### DateTime

- `LocalDateTime` for transaction timestamps
- Tracks when each transaction occurred

### 4. **SOLID Principles**

- **S**ingle Responsibility: Each class has one reason to change
- **O**pen/Closed: System is open for extension (new account types), closed for modification
- **L**iskov Substitution: `BankServiceImpl` can be substituted for `BankService`
- **I**nterface Segregation: Focused `BankService` interface
- **D**ependency Inversion: Depends on `BankService` interface, not concrete implementation

## Project Structure

```
src/
├── Main.java                 # Entry point with CLI menu
├── domain/                   # Data models
│   ├── Account.java         # Account entity
│   ├── Customer.java        # Customer entity
│   ├── Transaction.java     # Transaction entity
│   └── Type.java           # Transaction type enum
├── service/                 # Business logic
│   ├── BankService.java    # Service interface
│   └── impl/
│       └── BankServiceImpl.java  # Service implementation
├── repository/              # Data access
│   ├── AccountRepository.java
│   ├── CustomerRepository.java
│   └── TransactionRepository.java
├── exceptions/              # Custom exceptions
│   ├── AccountNotFoundException.java
│   ├── InsufficientBalanceException.java
│   └── ValidationException.java
└── util/                    # Helper utilities
    └── Validation.java      # Input validation
```

## How to Run

```bash
# Compile the project
javac -d . src/**/*.java

# Run the application
java Main
```

## Usage Example

1. Start the application
2. Choose "1" to open a new account
3. Enter customer details (name, email, account type)
4. Use other options to perform transactions
5. View statements and account details as needed
