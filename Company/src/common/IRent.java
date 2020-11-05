package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRent extends Remote {

	public IRenter getRenter() throws RemoteException;
	
	public IVehicle getVehicle() throws RemoteException;
	
}
