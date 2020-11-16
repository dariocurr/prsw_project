/**
 * Bank.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bank;

/** Interface to make the payments when clients buy vehicles. */
public interface Bank extends java.rmi.Remote {
	
	/** Make the payment of an amount of money from a given account number with a specified currency.
	 * @param accountNumber the account which receive the payment
	 * @param amount the amount of money of the payment
	 * @param currency the currency which is used for the payment
	 * @return true if the payment is done correctly, false otherwise */
    public boolean makePayment(java.lang.String accountNumber, double amount, java.lang.String currency) throws java.rmi.RemoteException;
    
    /** Convert the amount of money from euro to the given currency.
	 * @param currency the final currency we want to have
	 * @param amount the amount of money in euro to convert
	 * @return the amount of money converted */
    public double getExchangeFromEUR(java.lang.String currency, double amount) throws java.rmi.RemoteException;
}
