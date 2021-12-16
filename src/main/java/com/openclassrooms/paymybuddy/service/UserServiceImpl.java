package com.openclassrooms.paymybuddy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.model.Account;
import com.openclassrooms.paymybuddy.model.User;
import com.openclassrooms.paymybuddy.model.UserRole;
import com.openclassrooms.paymybuddy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	private final PasswordEncoder passwordEncoder;
	private AccountService accountService;

	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder, AccountService accountService) {
		this.passwordEncoder = passwordEncoder;
		this.accountService = accountService;
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("email %s not found", email)));

	}

	@Override
	public User createUser(User user) {

		boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

		if (userExists) {
			throw new IllegalStateException("email already taken");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.USER);

		User savedUser = userRepository.save(user);

		Account account = new Account(user);
		accountService.createAccount(account);
		// user.setAccounts(accounts);
		// Set<Account> accounts = Sets.newHashSet(account);

		return savedUser;
	}

	@Override
	public Optional<User> getUserInfo(String userEmail) {

		return userRepository.findByEmail(userEmail);
	}

}
