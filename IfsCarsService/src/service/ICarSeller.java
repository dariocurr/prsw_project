package service;

import java.rmi.RemoteException;
import java.util.List;

import common.IBasket;
import common.IVehicle;

public interface ICarSeller {

	public String getAvailableVehiclesForSale() throws RemoteException;
	public boolean sellVehicle(String basket, String accountNumber, double amount, String currency) throws RemoteException;
	
}
