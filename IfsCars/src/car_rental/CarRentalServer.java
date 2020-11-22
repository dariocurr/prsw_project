package car_rental;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import common.ICarRentalObservable;

/** Server RMI for the @CarRental object */
public class CarRentalServer {
	
	/** Main method to rebind the @carRental object
	 * @param args Unused */
	public static void main(String[] args) {
		
		try {
			
			LocateRegistry.createRegistry(1099);
			ICarRentalObservable carRental = new CarRental();
			System.setProperty("java.security.policy", "file:." + File.separator  + "server.policy");
			Naming.rebind("CarRentalService", carRental);
			System.out.println("Server running...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
}
