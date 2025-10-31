package service;

import domain.Account;

import java.util.List;

public interface BankService {
    String openAccount(String name, String email, String accountType);

    List<Account> listAccounts();


    void deposit(String accountNumber, Double amount, String deposit);
}
