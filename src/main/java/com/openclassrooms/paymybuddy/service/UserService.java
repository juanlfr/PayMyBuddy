package com.openclassrooms.paymybuddy.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.paymybuddy.model.User;

@Service
public interface UserService {

	User createUser(User user);
}
