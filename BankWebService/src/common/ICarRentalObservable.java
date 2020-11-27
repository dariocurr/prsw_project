package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface ICarRentalObservable extends Remote {
	
	/** Get the available vehicles 
	 * @return the list of the available vehicles */
	public List<IVehicle> getAvailableVehicles() throws RemoteException;
	
	/** Get the not-available vehicles, the vehicles currently rented
	 * @return the map with the vehicles and the date of the end of the rent  */
	public Map<IVehicle, String> getNotAvailableVehicles() throws RemoteException;
	
	/** Rent a vehicle
	 * @param renter the renter who rent the vehicle
	 * @param vehicle the vehicle rented
	 * @param startDate the beginning date of the rent
	 * @param endDate the end date of the rent 
	 * @param coupon the coupon
	 * @return the rent */
	public IRent rentVehicle(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException;
	
	/** Rent a vehicle
	 * @param rent the rent */
	public void rentVehicle(IRent rent) throws RemoteException;
	
	/** Return a vehicle rented
	 * @param rent the rent
	 * @param notes list of notes of the rent */
	public void returnVehicle(IRent rent, List<String> notes) throws RemoteException;
	
	/** Return a vehicle rented
	 * @param rent the rent */
	public void returnVehicle(IRent rent) throws RemoteException;
	
	/**Add a rent in the wait list 
	 * @param renter the renter of the rent
	 * @param vehicle the vehicle to be rented
	 * @param startDate the beginning date of the rent
	 * @param endDate the end date of the rent 
	 * @param coupon the coupon 
	 * @return the rent */
	public IRent attach(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException;
	
	/** Remove the rent from the wait list
	 * @param rent the rent
	 * @return true if the remove operation is done without errors, false otherwise*/
	public boolean detach(IRent rent) throws RemoteException;
	
	/** Notify that a vehicle has been returned
	 * @param vehicle the vehicle returned */
	public void notifyObserver(IVehicle vehicle) throws RemoteException;
	
	/** Get the list of rents of a renter
	 * @param renter the renter
	 * @return the list of rents */
	public List<IRent> getRenterRentals(IRenterObserver renter) throws RemoteException;
	
	/** Remove a vehicle
	 * @param vehicle the vehicle
	 * @return true if the remove operation is done correctly, false otherwise */
	public boolean removeVehicle(IVehicle vehicle) throws RemoteException;
	
}
