package com.openclassrooms.paymybuddy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.model.User;

@Service
public interface UserService {

	User createUser(User user);

	Optional<User> getUserInfo(String userEmail);
}
