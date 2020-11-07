package common;

public interface IBank {
	
	public boolean makePayment(String accountNumber, double amount, String currency);
	
	public double getExchangeFromEUR(String currency, double amount);
	
}
