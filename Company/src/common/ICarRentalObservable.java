package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICarRentalObservable extends Remote {
	
	public Rent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate) throws RemoteException;
	public void returnVehicle(IRent rent) throws RemoteException;
	public String test() throws RemoteException;
	public boolean attach(IRent rent, IVehicle vehicle) throws RemoteException;
	public boolean detach(IRent rent, IVehicle vehicle) throws RemoteException;
	public void notifyObserver(IVehicle vehicle) throws RemoteException;
	
}
