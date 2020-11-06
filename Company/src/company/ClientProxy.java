package company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import common.ARenter;
import common.ICarRentalObservable;
import common.IRent;
import common.IVehicle;
import common.Renter;

public class ClientProxy {
	
	public static void main(String[] args) {
		
		try {
			System.setProperty("java.security.policy","file:///home/dario/Documenti/Rest/progetto/prsw/Company/src/company/sec.policy");
			System.setProperty("java.rmi.server.codebase", "file:///home/dario/Documenti/Rest/progetto/prsw/CarRental/bin/");
			System.setSecurityManager(new RMISecurityManager());
			ICarRentalObservable carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
			ARenter renter = new Renter("cugino", "antonio", 22, "fafas@ga", "123", true);
			System.out.println("DISPONIBILI");
			for (IVehicle vehicle : carRental.getAvailableVehicles()) {
				System.out.println(vehicle.getModel());
			}
			carRental.rentVehicle(renter, carRental.getAvailableVehicles().get(0), "26/01/97" , "28/01/97", "EMP001");
			carRental.rentVehicle(renter, carRental.getAvailableVehicles().get(1), "29/01/97" , "30/01/97", "EMP002");
			System.out.println("\nAFFITTATE");
			for (IRent rent : carRental.getRenterRentals(renter)) {
				System.out.println(rent.getVehicle());
			}
			System.out.println("\nDISPONIBILI");
			for (IVehicle vehicle : carRental.getAvailableVehicles()) {
				System.out.println(vehicle.getModel());
			}
			System.out.println("\nLISTA ATTESA");
			for (IVehicle vehicle : carRental.getNotAvailableVehicles().keySet()) {
				System.out.println(vehicle.getModel() + " " + carRental.getNotAvailableVehicles().get(vehicle));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	@Override
	public boolean createRenter(String firstName, String lastName, String email, String password, String discountCode) {
		IRenter renter;
		if (discountCode == "EMP001") {
			renter = new Employee(firstName, lastName, email, password);
		} else {
			renter = new Renter(firstName, lastName, email, password, false);
		}
		this.renters.stream().forEach(v -> System.out.println(v));
		return this.renters.add(renter);
	} */
	
	
}
