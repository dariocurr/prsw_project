package car_rental;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import common.IPerson;

public class CarRental implements ICarRental {
	
	private List<IVehicle> availableVehicles;
	private List<IRent> rentals;
	
	public CarRental() {
		super();
		this.availableVehicles = new ArrayList<IVehicle>();
		this.rentals = new ArrayList<IRent>();
	}

	@Override
	public Rent rentVehicle(IPerson person, IVehicle vehicle, String startDate, String endDate) {
		Objects.requireNonNull(person);
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		Rent rent = new Rent(vehicle, person, startDate, endDate);
		this.rentals.add(rent);
		return rent;
	}

	@Override
	public void returnVehicle(Rent rent) {
		Objects.requireNonNull(rent);
		this.rentals.remove(rent);
		this.availableVehicles.add(rent.getVehicle());
	}

}
