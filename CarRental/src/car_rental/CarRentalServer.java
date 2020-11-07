package car_rental;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import common.ICarRentalObservable;

public class CarRentalServer {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1102);
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
