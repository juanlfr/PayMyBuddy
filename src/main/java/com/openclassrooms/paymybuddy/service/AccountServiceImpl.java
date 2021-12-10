package com.openclassrooms.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {

		return accountRepository.save(account);
	}

}
