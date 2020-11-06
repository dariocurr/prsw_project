package bank;

import java.util.Objects;

public class BankAccount {
	
	private String accountNumber;
	private double amount;
	
	public BankAccount(String accountNumber, double amount) {
		Objects.requireNonNull(accountNumber);
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getAmount() {
		return amount;
	}
	
}
