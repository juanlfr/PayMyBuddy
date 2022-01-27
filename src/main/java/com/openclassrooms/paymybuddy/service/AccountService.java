package com.openclassrooms.paymybuddy.service;

import java.util.Optional;

import com.openclassrooms.paymybuddy.exception.InsufficientBalanceException;
import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.BankAccount;
import com.openclassrooms.paymybuddy.model.Transaction;
import com.openclassrooms.paymybuddy.model.User;

public interface AccountService {

	Account createAccount(User user);

	BankAccount createBankAccount(BankAccount bankAccount);

	Iterable<Account> findAll();

	Optional<Account> getAccountById(Long id);

	void doTransaction(Transaction transaction) throws InsufficientBalanceException;

}
