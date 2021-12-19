package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.BankAccount;
import com.openclassrooms.paymybuddy.model.User;

public interface AccountService {

	Account createAccount(User user);

	BankAccount createBankAccount(BankAccount bankAccount);

	Iterable<Account> findAll();

}
