package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.IBasket;
import common.IVehicle;
import common.Vehicle;
import service.CarSeller;
import service.CarSellerServiceLocator;

/** Client class for the IfsCars Service */
public class IfsCarsServiceClient {
	
	private CarSeller service;

	/** Default constructor used to initialize the car seller */
	public IfsCarsServiceClient() throws ServiceException {
		this.service = (CarSeller) new CarSellerServiceLocator().getCarSeller();
	}
	
	/** Get the vehicles available for sale
	 * @return list of vehicles available for sale */
	public List<IVehicle> getVehicles() throws RemoteException {
		//List<IVehicle> vehicles = new ArrayList<>();
		//è normale sia vuota, perche nessun veicolo è pronto per la vendita
		// per test, ti conviene commentare riga 54 e 56 in CarSeller
		return reconstructFromJSONString(service.getAvailableForSaleVehicles());
	}
	
	/** Sell all the vehicles in the basket
	 * @param basket the basket where vehicles to be sold are placed
	 * @param accountNumber account number of the seller
	 * @param amount total amount of money
	 * @param currency currency of the payment
	 * @return true if the sell operation is done correctly, false otherwise */
	public boolean sell(IBasket basket, String accountNumber, Double amount, String currency) {
		System.out.println("sono in sell");
		try {
			return service.sellVehicle(createJSONString(basket.getVehiclesInBasket()), accountNumber, amount, currency);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("TODO sell vehicle error");
			e.printStackTrace();
		}
		return false;
	}
	
	public double convert(String currency, Double amount) throws RemoteException {
		return service.convert(currency, amount);
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
		List<IVehicle> listVehicles = new ArrayList<>();
		if(veichlesList != null) {
			String[] stringVehicles = veichlesList.split(";");
			
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
				JSONArray notes = (JSONArray) json.get("notes");
				List<String> listNotes = new ArrayList<>();
				for(Object note : notes)
					listNotes.add((String) note);
					
				IVehicle newVehicle = new Vehicle(model, year, seats.intValue(), doors.intValue(), transmission, size, pricePerDay, price, file_name);
				newVehicle.getNotes().addAll(listNotes);
				listVehicles.add(newVehicle);
			}
		}
		return listVehicles;
	}
	
}
