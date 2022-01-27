package com.openclassrooms.paymybuddy.service;

import org.springframework.stereotype.Service;

@Service
public interface FacturationService {

	void sendTaxedValue(Long accountId, double transactionTaxedValue);

}
