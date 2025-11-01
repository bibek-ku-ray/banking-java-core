package service.impl;

import domain.Account;
import domain.Transaction;
import domain.Type;
import exceptions.AccountNotFoundException;
import exceptions.InsufficientBalanceException;
import exceptions.ValidationException;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    AccountRepository accountRepository = new AccountRepository();
    TransactionRepository transactionRepository = new TransactionRepository();
    CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();
        String accountNumber = getAccountNumber();

        Account account = new Account(accountNumber, customerId, (double) 0, accountType);
        accountRepository.save(account);
        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    @Override
    public void deposit(String accountNumber, Double amount, String note) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "Account not found! Account number: " + accountNumber)
                );

        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction(
                note,
                LocalDateTime.now(),
                amount,
                accountNumber,
                Type.DEPOSIT,
                UUID.randomUUID().toString()
        );

        transactionRepository.add(transaction);
    }

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found! Account number: " + accountNumber)
                );

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("INSUFFICIENT BALANCE 😿");
        }

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction(
                note,
                LocalDateTime.now(),
                amount,
                accountNumber,
                Type.WITHDRAW,
                UUID.randomUUID().toString()
        );

        transactionRepository.add(transaction);
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount, String note) {
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new ValidationException("Invalid credentials");
        }

        Account fromAcc = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found!" + fromAccountNumber));

        Account toAcc = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found! " + toAccountNumber));

        if (fromAcc.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromAcc.setBalance(fromAcc.getBalance() - amount);
        toAcc.setBalance(toAcc.getBalance() + amount);

        transactionRepository.add(new Transaction(note,
                LocalDateTime.now(), amount, fromAccountNumber, Type.TRANSFER_OUT,
                UUID.randomUUID().toString()));

        transactionRepository.add(new Transaction(note,
                LocalDateTime.now(), amount, toAccountNumber, Type.TRANSFER_IN,
                UUID.randomUUID().toString()));


    }

    @Override
    public List<Transaction> statements(String accountNumber) {

        accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account Not found!"));

        return transactionRepository.findAll(accountNumber)
                .stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .toList();
    }

    @Override
    public List<Account> searchAccountByName(String name) {
        String q = (name == null) ? "" : name.toLowerCase();

        return customerRepository.findAll().stream()
                .filter(c -> c.getName().toLowerCase().contains(q))
                .flatMap(c -> accountRepository.findByCustomerId(c.getId()).stream())
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .toList();
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size();
        return String.format("AC%07d", size);
    }
}
