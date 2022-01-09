package com.openclassrooms.paymybuddy.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.exception.InsufficientBalanceException;
import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.BankAccount;
import com.openclassrooms.paymybuddy.model.IAccountFacade;
import com.openclassrooms.paymybuddy.model.Transaction;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private IAccountFacade accountFacade;
	@Autowired
	private TransactionService transactionService;

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

	@Override
	public Optional<Account> getAccountById(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public void doTransaction(Transaction transaction) {

		Account senderAccount = transaction.getSenderAccount();
		Account receieverAccount = transaction.getReceiverAccount();
		double amount = transaction.getAmount();
		double senderBalance = senderAccount.getBalance();

		try {

			if (senderBalance >= amount) {
				senderAccount.setBalance(senderBalance - amount);
				accountRepository.save(senderAccount);
				receieverAccount.setBalance(receieverAccount.getBalance() + amount);
				accountRepository.save(receieverAccount);
				transaction.setDate(LocalDateTime.now());
				transaction.setDescription(transaction.getDescription());
				transactionService.createTransaction(transaction);
			} else {
				throw new InsufficientBalanceException("Insufficient account balance");
			}
		} catch (InsufficientBalanceException e) {
			log.error("Error" + e.getMessage());
		}

	}

}
