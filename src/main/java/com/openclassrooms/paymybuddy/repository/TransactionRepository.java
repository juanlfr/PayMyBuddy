package com.openclassrooms.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paymybuddy.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
