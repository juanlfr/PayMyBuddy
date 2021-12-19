package com.openclassrooms.paymybuddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.BankAccount;
import com.openclassrooms.paymybuddy.model.IAccountFacade;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private IAccountFacade accountFacade;

	@Override
	public Account createAccount(User user) {

		Account account = accountFacade.getAccount();
		account.setUser(user);

		return accountRepository.save(account);
	}

	@Override
	public BankAccount createBankAccount(BankAccount bankAccount) {
		bankAccount.setUser(userService.getCurrentUser());
		return accountRepository.save(bankAccount);
	}

	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

}
