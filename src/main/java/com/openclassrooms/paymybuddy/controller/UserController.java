package com.openclassrooms.paymybuddy.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.collect.Sets;
import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.BankAccount;
import com.openclassrooms.paymybuddy.model.Transaction;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.service.UserService;

@Controller
public class UserController {

	private static final Logger log = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private AccountController accountController;

	@GetMapping("/")
	public String home() {
		return "login";
	}

	@GetMapping("/signUpForm")
	public ModelAndView showSignUpForm() {

		ModelAndView signUpForm = new ModelAndView("signUpForm");

		signUpForm.addObject("user", new User());

		return signUpForm;

	}

	@PostMapping("/signUpForm")
	public ModelAndView submitUserForm(User user) {

		RedirectView redirectView = new RedirectView();
		try {

			userService.createUser(user);
			accountController.createAccount(user);
			log.info("Creating user " + user);

			redirectView.setUrl("/");
		} catch (Exception e) {
			log.error("Error creating user: " + e);
			redirectView.setUrl("/error");
		}

		return new ModelAndView(redirectView);

	}

	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}

	@GetMapping("/welcome")
	public String welcomeRegisteredUser(Model model) {

		log.info("**********This is de user controller *************");

		User sessionUser = userService.getCurrentUser();
		User userFromDB = userService.getUserById(sessionUser.getUserId()).get();
		Set<Account> accounts = userFromDB.getAccounts();
		Set<Account> appAccounts = Sets.newHashSet(userFromDB.getAccounts());
		Set<BankAccount> bankAccounts = new HashSet<>();
		for (Iterator<Account> iterator = appAccounts.iterator(); iterator.hasNext();) {
			Account account = iterator.next();
			if (account instanceof BankAccount) {
				bankAccounts.add((BankAccount) account);
				iterator.remove();
			}
		}
		String transferUrl = String.format("/account/transactions/%s",
				appAccounts.stream().findFirst().get().getAccountId());
		model.addAttribute("transferUrl", transferUrl);
		model.addAttribute("userFullName", sessionUser.getFirstName() + " " + sessionUser.getLastName());
		model.addAttribute("appAccounts", appAccounts);
		model.addAttribute("bankAccounts", bankAccounts);
		model.addAttribute("accounts", accounts);
		model.addAttribute("transaction", new Transaction());

		return "welcome";
	}

	@GetMapping("/createConnection")
	public ModelAndView createConnectionForm() {

		ModelAndView createConnectionForm = new ModelAndView("createConnection");

		createConnectionForm.addObject("connection", new User());

		return createConnectionForm;
	}

	@PostMapping("/createConnection")
	public String findOrCreatUserConnection(User userEmail) {

		User sessionUser = userService.getCurrentUser();
		User userFromDB = userService.getUserById(sessionUser.getUserId()).get();
		Optional<User> registeredUser = userService.geUserByEmail(userEmail.getEmail());
		if (registeredUser.isPresent()) {
			Set<User> userConnections = userFromDB.getConnections();
			userConnections.add(registeredUser.get());
			userService.updateUser(userFromDB);
		} else {
			log.error("Email not found");
		}
		Stream<Account> appAcount = userFromDB.getAccounts().stream().filter(acct -> !(acct instanceof BankAccount));

		return String.format("redirect:account/transactions/%s", appAcount.findFirst().get().getAccountId().toString());

	}

}
