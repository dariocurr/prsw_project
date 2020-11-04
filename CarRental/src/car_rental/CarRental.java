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
	public Rent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate) throws RemoteException {
		Objects.requireNonNull(renter);
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		Rent rent = new Rent(renter, vehicle, startDate, endDate);
		this.availableVehicles.remove(vehicle);
		this.rentals.add(rent);
		return rent;
	}

	@Override
	public void returnVehicle(IRent rent) throws RemoteException {
		Objects.requireNonNull(rent);
		this.rentals.remove(rent);
		this.availableVehicles.add(rent.getVehicle());
	}

	@Override
	public boolean attach(IRent rent, IVehicle vehicle) throws RemoteException {
		return this.waitList.get(vehicle).add(rent);
	}

	@Override
	public boolean detach(IRent rent, IVehicle vehicle) throws RemoteException {
		return this.waitList.get(vehicle).remove(rent);
	}

	@Override
	public void notifyObserver(IVehicle vehicle) throws RemoteException {
		this.waitList.get(vehicle).get(0).getRenter().update();
	}

}
