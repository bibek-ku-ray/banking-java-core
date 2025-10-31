package repository;

import domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {
    private final Map<String, List<Transaction>> txnByAccount = new HashMap<>();

    public void add(Transaction transaction) {
        List<Transaction> transactionList = txnByAccount.computeIfAbsent(
                transaction.getAccountNumber(), k -> new ArrayList<>());
        transactionList.add(transaction);
    }
}
