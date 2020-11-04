package car_rental;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CarRentalServer {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			ICarRental carRental = new CarRental();
			Naming.rebind("CarRentalService", carRental);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
