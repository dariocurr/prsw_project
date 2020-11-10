package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.IBasket;
import common.IVehicle;
import common.Vehicle;
import service.CarSeller;
import service.CarSellerServiceLocator;

public class IfsCarsServiceClient {
	
	private CarSeller service;

	public IfsCarsServiceClient() throws ServiceException {
		this.service = (CarSeller) new CarSellerServiceLocator().getCarSeller();
	}
	
	public List<IVehicle> getVehicles() throws RemoteException {
		List<IVehicle> vehicles = new ArrayList<>();
		//� normale sia vuota, perche nessun veicolo � pronto per la vendita
		// per test, ti conviene commentare riga 54 e 56 in CarSeller
		return reconstructFromJSONString(service.getAvailableForSaleVehicles());
	}
	
	public boolean sell(IBasket basket, String accountNumber, Double amount, String currency) {
		try {
			return service.sellVehicle(createJSONString(basket.getVehiclesInBasket()), accountNumber, amount, currency);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("TODO sell vehicle error");
		}
		return false;
	}
	
	private String createJSONString(List<IVehicle> availableVehiclesForSale) {
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
			vehicles += json.toString() + ";";
		}
		return vehicles;
	}
	
	private List<IVehicle> reconstructFromJSONString(String veichlesList) {
		String[] stringVehicles = veichlesList.split(";");
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
	
}
