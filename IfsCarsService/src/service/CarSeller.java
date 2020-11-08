package service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//import javax.xml.rpc.ServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bank.BankServiceLocator;
import common.IBank;
import common.IBasket;
import common.ICarRentalObservable;
import common.IVehicle;

public class CarSeller implements ICarSeller {

	private ICarRentalObservable carRental;
	private IBank bank;
	
	public CarSeller() throws MalformedURLException, RemoteException, NotBoundException {
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		path = path.substring(0, path.lastIndexOf(File.separator));
		String policy_path = "file:" + File.separator + File.separator + path + File.separator + "EiffelCorp" + File.separator + "src" + File.separator + "company" + File.separator + "sec.policy";
		String codebase_path = "file:" + File.separator + File.separator + path + File.separator + "IfsCars" + File.separator + "bin" + File.separator;
		System.setProperty("java.security.policy", policy_path);
		System.setProperty("java.rmi.server.codebase", codebase_path);
		System.setSecurityManager(new RMISecurityManager());
		carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
		this.bank = (IBank) new BankServiceLocator().getBank();
	}
	
	@Override
	public String getAvailableVehiclesForSale() throws RemoteException {
		// JSON TO RETURN
		String vehicles = new String();
		List<IVehicle> availableVehiclesForSale = this.carRental.getAvailableVehicles().stream().filter(IVehicle::isForSale).collect(Collectors.toList());
		//return (Vehicle[]) availableVehiclesForSale.toArray(new IVehicle[availableVehiclesForSale.size()]);
		
		for(IVehicle vehicle : availableVehiclesForSale) {
			JSONObject json = new JSONObject();
			
			json.put("model", vehicle.getModel());
			json.put("year", vehicle.getYear());
			json.put("seats", vehicle.getSeats());
			json.put("doors", vehicle.getDoors());
			json.put("transmission", vehicle.getTrasmission());
			json.put("pricePerDay", vehicle.getPricePerDay());
			json.put("price", vehicle.getPrice());
			json.put("size", vehicle.getSize());
			json.put("fileName", vehicle.getFileName());
			
			vehicles += ((JSONObject) vehicle).toString();
		}		
		
		return vehicles;
	}
	
	@Override
	public boolean sellVehicle(String basket, String accountNumber, double amount, String currency) throws RemoteException {
		Objects.requireNonNull(basket);
		Objects.requireNonNull(accountNumber);
		Objects.requireNonNull(currency);
		boolean isPaymentDone = this.bank.makePayment(accountNumber, amount, currency);
		// RECONSTRUCT VEHICLES
		/*
		if (isPaymentDone) {
			this.carRental.getAvailableVehicles().removeAll(basket.getVehiclesInBasket());
		}*/
		return isPaymentDone;
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		CarSeller carSeller = new CarSeller();
		
		String vehicles = carSeller.getAvailableVehiclesForSale();
		
		System.out.println(vehicles);
	}
	
}
