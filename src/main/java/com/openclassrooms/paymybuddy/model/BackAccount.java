package com.openclassrooms.paymybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class BackAccount extends Account {

	@Column(nullable = false)
	private String iban;
	private String bankName;

	public BackAccount() {
	}

	public BackAccount(String iban, String bankName) {
		super();
		this.iban = iban;
		this.bankName = bankName;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
