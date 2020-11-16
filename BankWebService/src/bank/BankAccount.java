package bank;

import java.util.Objects;

import common.IBankAccount;

/** Class which implements the @IBankAccount interface */
public class BankAccount implements IBankAccount {
	
	private String accountNumber;
	private double amount;
	
	/** Main constructor used to set the account number and the amount of money 
	 * @param accountNumber the account number of the bank account
	 * @param amount the amount of money inside the bank account */
	public BankAccount(String accountNumber, double amount) {
		Objects.requireNonNull(accountNumber);
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	/** {@inheritDoc} */
	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	/** {@inheritDoc} */
	@Override
	public double getAmount() {
		return amount;
	}

	/** {@inheritDoc} */
	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
