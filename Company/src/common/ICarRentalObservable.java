package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICarRentalObservable extends Remote {
	
	public List<IVehicle> getAvailableVehicles() throws RemoteException;
	public IRent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException;
	public void rentVehicle(IRent rent) throws RemoteException;
	public void returnVehicle(IRent rent, List<String> notes) throws RemoteException;
	public void returnVehicle(IRent rent) throws RemoteException;
	public IRent attach(IRenter renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException;
	public boolean detach(IRent rent) throws RemoteException;
	public void notifyObserver(IVehicle vehicle) throws RemoteException;
	public List<IRent> getRenterRentals(IRenter renter) throws RemoteException; 
	
}
