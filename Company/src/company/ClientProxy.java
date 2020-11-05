package company;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.ICarRentalObservable;
import common.IRent;
import common.IRenter;
import common.IVehicle;
import common.Renter;

public class ClientProxy {
	
	public static void main(String[] args) {
		
		try {
			ICarRentalObservable carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
			IRenter renter = new Renter("cugino", "antonio", "fafas@ga", "123", true);
			for (IVehicle vehicle : carRental.getAvailableVehicles()) {
				System.out.println(vehicle);
			}
			carRental.rentVehicle(renter, carRental.getAvailableVehicles().get(0), "26/01/97" , "28/01/97", "EMP001");
			carRental.rentVehicle(renter, carRental.getAvailableVehicles().get(1), "29/01/97" , "30/01/97", "EMP001");
			for (IRent rent : carRental.getRenterRentals(renter)) {
				System.out.println(rent);
			}
			for (IVehicle vehicle : carRental.getNotAvailableVehicles().keySet()) {
				System.out.println(vehicle + " " + carRental.getNotAvailableVehicles().get(vehicle));
			}
			/*
			System.setProperty("java.security.policy", "/Company/src/company/sec.policy");
			System.setProperty("java.rmi.server.codebase", "/Company/bin");
			System.setSecurityManager(new RMISecurityManager());
			*/
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
