package car_rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import common.IRenter;
import common.IVehicle;
import common.Observable;
import common.Rent;

public interface ICarRental extends Observable, Remote {
	
	public Rent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate) throws RemoteException;
	public void returnVehicle(Rent rent) throws RemoteException;
	
}
