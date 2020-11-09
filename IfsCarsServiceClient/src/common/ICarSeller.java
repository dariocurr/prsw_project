package common;

import java.rmi.RemoteException;
import java.util.List;

public interface ICarSeller {

	public String getAvailableForSaleVehicles() throws RemoteException;
	public boolean sellVehicle(String basket, String accountNumber, double amount, String currency) throws RemoteException;
	
}
