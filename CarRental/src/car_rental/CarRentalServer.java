package car_rental;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import common.ICarRentalObservable;

public class CarRentalServer {
	public static void main(String[] args) {
		try {
<<<<<<< HEAD
			LocateRegistry.createRegistry(1099);
=======
			LocateRegistry.createRegistry(1103);
>>>>>>> 5a338146fb93a80b0b5380c351dc8167f87d4a21
			ICarRentalObservable carRental = new CarRental();
			Naming.rebind("CarRentalService", carRental);
			System.out.println("Server running...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
