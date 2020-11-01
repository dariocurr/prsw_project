package car_rental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import common.IRenter;
import common.IVehicle;
import common.Observable;

public class CarRental implements ICarRental, Observable {
	
	private List<IVehicle> availableVehicles;
	private List<IRent> rentals;
	private Map<IVehicle, List<IRenter>> waitList;
	
	public CarRental() {
		super();
		this.availableVehicles = new ArrayList<IVehicle>();
		this.rentals = new ArrayList<IRent>();
		this.waitList = new HashMap<IVehicle, List<IRenter>>();
	}

	public List<IVehicle> getAvailableVehicles() {
		return this.availableVehicles;
	}
	
	@Override
	public Rent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate) {
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
	public void returnVehicle(Rent rent) {
		Objects.requireNonNull(rent);
		this.rentals.remove(rent);
		this.availableVehicles.add(rent.getVehicle());
	}

	@Override
	public boolean attach(IRenter renter, IVehicle vehicle) {
		return this.waitList.get(vehicle).add(renter);
	}

	@Override
	public boolean detach(IRenter renter, IVehicle vehicle) {
		return this.waitList.get(vehicle).remove(renter);
	}

	@Override
	public void notifyObservers(IVehicle vehicle) {
		this.waitList.get(vehicle).get(0).update();		
	}

}
