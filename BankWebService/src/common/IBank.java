package common;

/** Interface to make the payments when clients buy vehicles. */
public interface IBank {
	
	/** Make the payment of an amount of money to a given account number with a specified currency.
	 * @param accountNumber the account which receive the payment
	 * @param amount the amount of money of the payment
	 * @param currency the currency which is used for the payment
	 * @return true if the payment is done correctly, false otherwise */
	public boolean makePayment(String accountNumber, double amount, String currency);
	
	/** Convert the amount of money from euro to the given currency.
	 * @param currency the final currency we want to have
	 * @param amount the amount of money in euro to convert
	 * @return the amount of money converted */
	public double getExchangeFromEUR(String currency, double amount);
	
}