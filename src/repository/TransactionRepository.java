package repository;

import domain.Transaction;

import java.util.*;

public class TransactionRepository {
    private final Map<String, List<Transaction>> txnByAccount = new HashMap<>();

    public void add(Transaction transaction) {
        List<Transaction> transactionList = txnByAccount.computeIfAbsent(
                transaction.getAccountNumber(), k -> new ArrayList<>());
        transactionList.add(transaction);
    }

    public List<Transaction> findAll(String accountNumber) {
        return new ArrayList<>(txnByAccount.getOrDefault(accountNumber, Collections.emptyList()));
    }
}
