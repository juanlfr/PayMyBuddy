package com.openclassrooms.paymybuddy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.openclassrooms.paymybuddy.service.TransactionService;

@Controller
public class TransactionController {
	private static final Logger log = LogManager.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

}
