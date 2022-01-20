package com.openclassrooms.paymybuddy.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.BankAccount;
import com.openclassrooms.paymybuddy.model.Transaction;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	private static final Logger log = LogManager.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@GetMapping("/createBankAccount")
	public ModelAndView createBankAccountForm() {
		ModelAndView createBankAccountForm = new ModelAndView("createBankAccount");

		createBankAccountForm.addObject("bankAccount", new BankAccount());

		return createBankAccountForm;
	}

	@PostMapping("/createBankAccount")
	public String createBankAccountSubmit(@ModelAttribute("bankAccount") BankAccount bankAccount) {

		try {

			accountService.createBankAccount(bankAccount);
			log.info("Creating bank account " + bankAccount.toString());

			return "redirect:/welcome";

		} catch (Exception e) {
			log.error("Error creating bank account: " + e);
			return "/error";
		}

	}

	public void createAccount(User user) {
		accountService.createAccount(user);
		log.info("Creating account for user" + user.toString());

	}

	@GetMapping("/myBankAccount/{id}")
	public String getBankAccountInfo(@PathVariable("id") Long id, Model model) {

		Account bankAccount = accountService.getAccountById(id).get();
		List<Transaction> creditTransactions = bankAccount.getCreditTransactions();
		List<Transaction> debitTransactions = bankAccount.getDebitTransactions();
		model.addAttribute("debitTransactions", debitTransactions);
		model.addAttribute("creditTransactions", creditTransactions);
		model.addAttribute("bankAccount", bankAccount);
		return "MyBankAccount";

	}

	@GetMapping("/transactions/{id}")
	public String createConnection(@PathVariable("id") Long id, Model model) {

		Account account = accountService.getAccountById(id).get();
		List<Transaction> creditTransactions = account.getCreditTransactions();
		List<Transaction> debitTransactions = account.getDebitTransactions().stream()
				.filter(transaction -> !(transaction.getReceiverAccount() instanceof BankAccount))
				.collect(Collectors.toList());
		model.addAttribute("debitTransactions", debitTransactions);
		model.addAttribute("creditTransactions", creditTransactions);
		model.addAttribute("account", account);
		Set<User> userConnections = account.getUser().getConnections();
		for (User user : userConnections) {
			Set<Account> appAcounts = user.getAccounts().stream().filter(acct -> !(acct instanceof BankAccount))
					.collect(Collectors.toSet());
			user.setAccounts(appAcounts);
		}
		model.addAttribute("userConnections", userConnections);
		model.addAttribute("transaction", new Transaction());

		return "transactions";
	}

	@PostMapping("/transfertMoneyBetweenAcounts")
	public String transfertMoneyBetweenAcounts(Transaction transaction) {

		log.info("****Transaction *****");
		if (transaction != null) {
			accountService.doTransaction(transaction);
		}
		return "redirect:/welcome";

	}

}
