package com.openclassrooms.paymybuddy.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements IAuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();

	}

}
