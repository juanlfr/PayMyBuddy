package com.openclassrooms.paymybuddy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.openclassrooms.paymybuddy.exception.InsufficientBalanceException;
import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.Transaction;
import com.openclassrooms.paymybuddy.repository.AccountRepository;
import com.openclassrooms.paymybuddy.service.AccountServiceImpl;
import com.openclassrooms.paymybuddy.service.TransactionService;

@SpringBootTest
public class AccountServiceTest {

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private TransactionService transactionService;

	@Autowired
	private AccountServiceImpl accountService;

	private Transaction transaction;
	private Account receiverAccount;
	private Account senderAccount;

	@BeforeEach
	private void setUpTest() {
		receiverAccount = new Account();
		receiverAccount.setBalance(0);
		senderAccount = new Account();
		senderAccount.setBalance(200);
		transaction = new Transaction();
		transaction.setAmount(100);
		transaction.setReceiverAccount(receiverAccount);
		transaction.setSenderAccount(senderAccount);
		transaction.setDescription("Test");

	}

	@Test
	public void doTransactionTest() throws InsufficientBalanceException {

		when(accountRepository.save(senderAccount)).thenReturn(senderAccount);
		when(accountRepository.save(receiverAccount)).thenReturn(receiverAccount);
		accountService.doTransaction(transaction);
		verify(transactionService, Mockito.times(1)).createTransaction(transaction);
		assertThat(senderAccount.getBalance()).isEqualTo(99.5);
		assertThat(receiverAccount.getBalance()).isEqualTo(100);

	}

}
