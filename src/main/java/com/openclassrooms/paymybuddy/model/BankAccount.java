package com.openclassrooms.paymybuddy.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "account_id")
public class BankAccount extends Account {

	@Column(nullable = false)
	private String iban;
	private String bankName;
	private double balance = 100;

	public BankAccount() {
	}

	public BankAccount(String iban, String bankName) {
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

	public double getBalance() {
		BigDecimal bd = new BigDecimal(balance).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public void setBalance(double balance) {
		BigDecimal bd = new BigDecimal(balance).setScale(2, RoundingMode.HALF_UP);
		this.balance = bd.doubleValue();
	}

	@Override
	public String toString() {
		return "BankAccount [iban=" + iban + ", bankName=" + bankName + ", balance=" + balance + "]";
	}

}
