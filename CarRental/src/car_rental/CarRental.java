package car_rental;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import common.ICarRentalObservable;
import common.IRent;
import common.IRenter;
import common.IVehicle;

public class CarRental extends UnicastRemoteObject implements ICarRentalObservable {
	
	private List<IVehicle> vehicles;
	private List<IVehicle> availableVehicles;
	private Map<IRenter, List<IRent>> rentals;
	private Map<IVehicle, List<IRent>> waitList;

	public CarRental() throws RemoteException {
		this.vehicles = this.loadVehiclesFromFile();
		this.availableVehicles = new ArrayList<IVehicle>();
		this.rentals = new HashMap<IRenter, List<IRent>>();
		this.waitList = new HashMap<IVehicle, List<IRent>>();
		for (IVehicle vehicle : this.vehicles) {
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
	public IRent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException {
		IRent rent = this.createRent(renter, vehicle, startDate, endDate, coupon);
		this.availableVehicles.remove(vehicle);
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
	public IRent attach(IRenter renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException {
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
	
	private IRent createRent(IRenter renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException {
		Objects.requireNonNull(renter);
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		Objects.requireNonNull(coupon);
		double discount = 0;
		if (coupon == "EMP001") {
			discount = 0.10;
		}
		return new Rent(renter, vehicle, startDate, endDate, discount);
	}
	
	private boolean insertRent(IRenter renter, IRent rent) {
		Objects.requireNonNull(renter);
		Objects.requireNonNull(rent);
		if (this.rentals.get(renter) == null) {
			this.rentals.put(renter, new ArrayList<IRent>());
		}
		return this.rentals.get(renter).add(rent);
	}

	@Override
	public List<IRent> getRenterRentals(IRenter renter) throws RemoteException {
		return this.rentals.get(renter);
	}
	
	private ArrayList<IVehicle> loadVehiclesFromFile() {
		return null;
	}
	

}
