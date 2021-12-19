package com.openclassrooms.paymybuddy.model;

import org.springframework.stereotype.Component;

@Component
public class IAccountFacadeImpl implements IAccountFacade {

	@Override
	public Account getAccount() {
		return new Account();
	}

}
