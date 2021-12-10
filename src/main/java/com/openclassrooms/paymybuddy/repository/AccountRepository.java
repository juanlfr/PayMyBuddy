package com.openclassrooms.paymybuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.paymybuddy.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
