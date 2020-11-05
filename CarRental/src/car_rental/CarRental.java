package car_rental;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import common.Employee;
import common.ICarRentalObservable;
import common.IRent;
import common.IRenter;
import common.IVehicle;
import common.Rent;

public class CarRental extends UnicastRemoteObject implements ICarRentalObservable {

	private List<IVehicle> availableVehicles;
	private List<IRent> rentals;
	private Map<IVehicle, List<IRent>> waitList;

	public CarRental() throws RemoteException {
		this.availableVehicles = new ArrayList<IVehicle>();
		this.rentals = new ArrayList<IRent>();
		this.waitList = new HashMap<IVehicle, List<IRent>>();
	}

	public List<IVehicle> getAvailableVehicles() {
		return this.availableVehicles;
	}

	@Override
	public IRent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate) throws RemoteException {
		IRent rent = this.createRent(renter, vehicle, startDate, endDate);
		this.availableVehicles.remove(vehicle);
		this.rentals.add(rent);
		return rent;
	}
	
	@Override
	public void rentVehicle(IRent rent) throws RemoteException {
		Objects.requireNonNull(rent);
		this.availableVehicles.remove(rent.getVehicle());
		this.rentals.add(rent);
	}
	
	@Override
	public void returnVehicle(IRent rent) throws RemoteException {
		Objects.requireNonNull(rent);
		this.rentals.remove(rent);
		this.availableVehicles.add(rent.getVehicle());
		this.notifyObserver(rent.getVehicle());
	}

	@Override
	public void returnVehicle(IRent rent, List<String> notes) throws RemoteException {
		Objects.requireNonNull(notes);
		this.returnVehicle(rent);
		if (rent.getRenter() instanceof Employee) {
			rent.getVehicle().getNotes().addAll(notes);
		} else {
			// throw new exception
		}
	}

	@Override
	public IRent attach(IRenter renter, IVehicle vehicle, String startDate, String endDate) throws RemoteException {
		IRent rent = this.createRent(renter, vehicle, startDate, endDate);
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
	
	public IRent createRent(IRenter renter, IVehicle vehicle, String startDate, String endDate) {
		Objects.requireNonNull(renter);
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		double discount = 0;
		if (renter instanceof Employee) {
			discount = 0.10;
		}
		return new Rent(renter, vehicle, startDate, endDate, discount);
	}

}
