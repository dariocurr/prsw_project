package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface ICarRentalObservable extends Remote {
	
	public List<IVehicle> getAvailableVehicles() throws RemoteException;
	public Map<IVehicle, String> getNotAvailableVehicles() throws RemoteException;
	public IRent rentVehicle(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException;
	public void rentVehicle(IRent rent) throws RemoteException;
	public void returnVehicle(IRent rent, List<String> notes) throws RemoteException;
	public void returnVehicle(IRent rent) throws RemoteException;
	public IRent attach(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException;
	public boolean detach(IRent rent) throws RemoteException;
	public void notifyObserver(IVehicle vehicle) throws RemoteException;
	public List<IRent> getRenterRentals(IRenterObserver renter) throws RemoteException;
	public boolean removeVehicle(IVehicle vehicle) throws RemoteException;
	
}
