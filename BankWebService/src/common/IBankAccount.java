package common;

/** Set and get all the informations about a bank account */
public interface IBankAccount {

	/** Get the account number of a given bank account 
	 * @return the account number */
	public String getAccountNumber();

	/** Get the amount of money inside a given bank account 
	 * @return the amount of money inside the bank account */
	public double getAmount();
	
	/** Set the amount of money inside a given bank account
	 * @param the amount of money to set */
	public void setAmount(double amount);
	
}
