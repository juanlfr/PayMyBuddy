package com.openclassrooms.paymybuddy.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@ManyToOne
	@JoinColumn(name = "sender_account_Id")
	private Account senderAccount;
	@ManyToOne
	@JoinColumn(name = "receiver_account_Id")
	private Account receiverAccount;

	private String description;

	private double amount;

	private LocalDateTime date;

	public Transaction() {
	}

	public Transaction(Account senderAccount, Account receiverAccount, String description, double amount,
			LocalDateTime date) {
		super();
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.description = description;
		this.amount = amount;
		this.date = date;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
