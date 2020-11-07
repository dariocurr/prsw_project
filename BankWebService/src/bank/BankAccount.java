package bank;

import java.util.Objects;

public class BankAccount implements IBankAccount {
	
	private String accountNumber;
	private double amount;
	
	public BankAccount(String accountNumber, double amount) {
		Objects.requireNonNull(accountNumber);
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
