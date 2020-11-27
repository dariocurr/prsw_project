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
import java.util.function.Consumer;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bank.Bank;
import bank.BankServiceLocator;
import common.IBank;
import common.ICarRentalObservable;
import common.ICarSeller;
import common.IVehicle;
import common.Vehicle;

/** Class which implements @ICarSeller interface */
public class CarSeller implements ICarSeller {

	private ICarRentalObservable carRental;
	private Bank bank;
	
	/** Default constructor used to initialize the bank service */
	public CarSeller() throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
		this.bank = (Bank) new BankServiceLocator().getBank();
	}
	
	/** {@inheritDoc} */
	@Override
	public String getAvailableForSaleVehicles() throws RemoteException {
		List<IVehicle> availableForSaleVehicles = new ArrayList<IVehicle>();
		
		for (IVehicle vehicle : carRental.getAvailableVehicles()) {
			if (vehicle.isForSale()) {
				availableForSaleVehicles.add(vehicle);
				System.out.println(vehicle.getNotes());
			}
		}
		if(!availableForSaleVehicles.isEmpty())
			return CarSeller.createJSONString(availableForSaleVehicles);
		else return null;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean sellVehicle(String basket, String accountNumber, double amount, String currency) throws RemoteException {
		Objects.requireNonNull(basket);
		Objects.requireNonNull(accountNumber);
		Objects.requireNonNull(currency);
		boolean isPaymentDone = this.bank.makePayment(accountNumber, amount, currency);
		if (isPaymentDone) {
			for(IVehicle iVehicle : CarSeller.reconstructFromJSONString(basket)) {
				this.carRental.removeVehicle(iVehicle);
			}
		}
		return isPaymentDone;
	}
	
	
	@SuppressWarnings("unchecked")
	private static String createJSONString(List<IVehicle> availableVehiclesForSale) {
		String vehicles = new String();
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
			json.put("notes", vehicle.getNotes());
			vehicles += json.toString() + ";";
		}
		return vehicles;
	}
	
	private static List<IVehicle> reconstructFromJSONString(String basket) {
		String[] stringVehicles = basket.split(";");
		List<IVehicle> listVehicles = new ArrayList<>();
		JSONParser parser = new JSONParser();
		for(String vehicle : stringVehicles) {
			JSONObject json = new JSONObject();
			try {
				json = (JSONObject) parser.parse(vehicle);
			} catch (ParseException e) {
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
			IVehicle newVehicle = new Vehicle(model, year, seats.intValue(), doors.intValue(), transmission, size, pricePerDay, price, file_name);
			listVehicles.add(newVehicle);
		}
		return listVehicles;
	}
	
	public double convert(String currency, Double amount) throws RemoteException {
		return this.bank.getExchangeFromEUR(currency, amount);
	}
	
}
