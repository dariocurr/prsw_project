package car_rental;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import common.ICarRentalObservable;
import common.IRent;
import common.IRenterObserver;
import common.IVehicle;

public class CarRental extends UnicastRemoteObject implements ICarRentalObservable {
	
	private List<IVehicle> availableVehicles;
	private Map<IRenterObserver, List<IRent>> rentals;
	private Map<IVehicle, List<IRent>> waitList;

	public CarRental() throws RemoteException {
		this.availableVehicles = this.loadVehiclesFromFile("res/car_list.json");
		this.rentals = new HashMap<IRenterObserver, List<IRent>>();
		this.waitList = new HashMap<IVehicle, List<IRent>>();
		for (IVehicle vehicle : this.availableVehicles) {
			this.waitList.put(vehicle, new ArrayList<IRent>());
		}
	}
	
	@Override
	public List<IVehicle> getAvailableVehicles() throws RemoteException {
		return this.availableVehicles;
	}
	
	@Override
	public Map<IVehicle, String> getNotAvailableVehicles() throws RemoteException {
		Map<IVehicle, String> notAvailableVehicles = new HashMap<IVehicle, String>();
		for (IVehicle vehicle : this.waitList.keySet()) {
			List<IRent> vehicleWaitList = this.waitList.get(vehicle);
			if (!vehicleWaitList.isEmpty()) {
				notAvailableVehicles.put(vehicle, vehicleWaitList.get(vehicleWaitList.size() - 1).getEndDate());
			}
			
		}
		for (List<IRent> renterRentals : this.rentals.values()) {
			for (IRent rent : renterRentals) {
				if (!notAvailableVehicles.containsKey(rent.getVehicle())) {
					notAvailableVehicles.put(rent.getVehicle(), rent.getEndDate());
				}
			}
		}
		return notAvailableVehicles;
	}

	@Override
	public IRent rentVehicle(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException {
		IRent rent = this.createRent(renter, vehicle, startDate, endDate, coupon);
		this.availableVehicles.remove(rent.getVehicle());
		this.insertRent(renter, rent);
		return rent;
	}
	
	@Override
	public void rentVehicle(IRent rent) throws RemoteException {
		Objects.requireNonNull(rent);
		this.availableVehicles.remove(rent.getVehicle());
		this.insertRent(rent.getRenter(), rent);
	}
	
	@Override
	public void returnVehicle(IRent rent) throws RemoteException {
		Objects.requireNonNull(rent);
		this.rentals.remove(rent.getRenter()).remove(rent);
		this.availableVehicles.add(rent.getVehicle());
		this.notifyObserver(rent.getVehicle());
	}

	@Override
	public void returnVehicle(IRent rent, List<String> notes) throws RemoteException {
		Objects.requireNonNull(notes);
		this.returnVehicle(rent);
		if (rent.getRenter().isTrusted()) {
			rent.getVehicle().getNotes().addAll(notes);
		} else {
			// throw new exception
		}
	}

	@Override
	public IRent attach(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException {
		IRent rent = this.createRent(renter, vehicle, startDate, endDate, coupon);
		this.waitList.get(vehicle).add(rent);
		return rent;
	}

	@Override
	public boolean detach(IRent rent) throws RemoteException {
		Objects.requireNonNull(rent);
		return this.waitList.get(rent.getVehicle()).remove(rent);
	}

	@Override
	public void notifyObserver(IVehicle vehicle) throws RemoteException {
		Objects.requireNonNull(vehicle);
		List<IRent> waitlist = this.waitList.get(vehicle);
		if (!waitlist.isEmpty()) {
			waitlist.get(0).getRenter().update();
		}
		this.waitList.get(vehicle).remove(0);
	}
	
	@Override
	public List<IRent> getRenterRentals(IRenterObserver renter) throws RemoteException {
		Objects.requireNonNull(renter);
		return this.rentals.get(renter);
	}
	
	@Override
	public List<IVehicle> getAvailableVehiclesForSale() throws RemoteException {
		return this.availableVehicles.stream().filter(IVehicle::isForSale).collect(Collectors.toList());
	}
	
	@Override
	public void sellVehicle(IVehicle vehicle) throws RemoteException {
		this.availableVehicles.remove(vehicle);
		this.waitList.remove(vehicle);
	}
	
	private IRent createRent(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException {
		Objects.requireNonNull(renter);
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		Objects.requireNonNull(coupon);
		double discount = 0;
		if (coupon == "EMP001") {
			discount = 0.10;
		}
		vehicle.setForSale(true);
		return new Rent(renter, vehicle, startDate, endDate, discount);
	}
	
	private boolean insertRent(IRenterObserver renter, IRent rent) {
		Objects.requireNonNull(renter);
		Objects.requireNonNull(rent);
		if (this.rentals.get(renter) == null) {
			this.rentals.put(renter, new ArrayList<IRent>());
		}
		return this.rentals.get(renter).add(rent);
	}
	

	private List<IVehicle> loadVehiclesFromFile(String url) throws RemoteException{
		JSONParser jsonParser = new JSONParser();
		
		List<IVehicle> vehiclesList = new ArrayList<>();
		
		try (FileReader reader = new FileReader(url))
        {
            Object obj = jsonParser.parse(reader);
 
            JSONArray vehicles = (JSONArray) obj;
            
            for(Object vehicle : vehicles) {
            	vehiclesList.add(parseVehiclesObject( (JSONObject) vehicle));
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return vehiclesList;
	}
	
	private static IVehicle parseVehiclesObject(JSONObject vehicle) throws RemoteException {
		
		String model = (String) vehicle.get("model");
		String year = (String) vehicle.get("year"); 
		String seats = (String) vehicle.get("seats");
		String doors = (String) vehicle.get("doors");
		String transmission = (String) vehicle.get("transmission");
		String pricePerDay = (String) vehicle.get("price_per_day");
		String price = (String) vehicle.get("price");
		String size = (String) vehicle.get("size");
		String file_name = (String) vehicle.get("file_name");
	
		IVehicle vehicle_obj = new Vehicle(model, year, Integer.parseInt(seats), Integer.parseInt(doors), transmission, size, Double.parseDouble(pricePerDay), Double.parseDouble(price), file_name);
         
        return vehicle_obj;
    }
}
