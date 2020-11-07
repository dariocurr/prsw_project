package bank;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public interface IBank {
	
	public boolean makePayment(String accountNumber, double amount, String currency) throws ServiceException, RemoteException;
	
	public double getExchangeFromEUR(String currency, double amount) throws ServiceException, RemoteException;
	
}
