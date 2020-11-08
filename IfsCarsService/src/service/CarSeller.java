package service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bank.BankServiceLocator;
import common.IBank;
import common.IBasket;
import common.ICarRentalObservable;
import common.IVehicle;
import common.Vehicle;

public class CarSeller implements ICarSeller {

	private ICarRentalObservable carRental;
	private IBank bank;
	
	public CarSeller() throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
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
		List<Object> availableVehiclesForSale = this.carRental.getAvailableVehicles().stream().filter(IVehicle::isForSale).collect(Collectors.toList());
		//return (Vehicle[]) availableVehiclesForSale.toArray(new IVehicle[availableVehiclesForSale.size()]);
		
		for(Object vehicle : availableVehiclesForSale) {
			IVehicle v = (IVehicle) vehicle;
			JSONObject json = new JSONObject();
			
			json.put("model", v.getModel());
			json.put("year", v.getYear());
			json.put("seats", v.getSeats());
			json.put("doors", v.getDoors());
			json.put("transmission", v.getTrasmission());
			json.put("pricePerDay", v.getPricePerDay());
			json.put("price", v.getPrice());
			json.put("size", v.getSize());
			json.put("fileName", v.getFileName());
			
			vehicles += json.toString() + ";";
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
		
		String[] stringVehicles = basket.split(";");
		List<IVehicle> listVehicles = new ArrayList<>();
		JSONParser parser = new JSONParser();
		
		for(String vehicle : stringVehicles) {
			JSONObject json = new JSONObject();
			try {
				json = (JSONObject) parser.parse(vehicle);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String model = (String) json.get("model");
			String year = (String) json.get("year"); 
			Long seats = (Long) json.get("seats");
			Long doors = (Long) json.get("doors");
			String transmission = (String) json.get("transmission");
			Double pricePerDay = (Double) json.get("pricePerDay");
			Double price = (Double) json.get("price");
			String size = (String) json.get("size");
			String file_name = (String) json.get("fileName");
			IVehicle v = new Vehicle(model, year, seats.intValue(), doors.intValue(), transmission, size, pricePerDay, price, file_name);
			listVehicles.add(v);
		}
			
			
		return isPaymentDone;
	}
}
