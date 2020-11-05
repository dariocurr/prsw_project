package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVehicle extends Remote {

	public String getModel() throws RemoteException;
	public List<String> getNotes() throws RemoteException;
	public double getPricePerDay() throws RemoteException;
	
}
