package common;

import java.rmi.RemoteException;
import java.util.List;

/** Interface for the car seller */
public interface ICarSeller {

	/** Get all the vehicles which are available for sale
	 * @return the json string of the list of vehicles available for sale */
	public String getAvailableForSaleVehicles() throws RemoteException;
	
	/** Sell  vehicles in the basket 
	 * @param basket the basket 
	 * @param accountNumber the account number of the client
	 * @param amount the amount of money of the payment
	 * @param currency the currency which is used for the payment 
	 * @return true if the payment is done correctly, false otherwise */
	public boolean sellVehicle(String basket, String accountNumber, double amount, String currency) throws RemoteException;
	
}
