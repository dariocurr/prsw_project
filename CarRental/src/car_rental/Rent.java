package car_rental;

import java.util.Objects;

import common.IPerson;

public class Rent implements IRent {
	
	private IVehicle vehicle;
	private IPerson person;
	private String startDate;
	private String endDate;
	
	public Rent(IVehicle vehicle, IPerson person, String startDate, String endDate) {
		super();
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(person);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		this.vehicle = vehicle;
		this.person = person;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public IVehicle getVehicle() {
		return this.vehicle;
	}
	
}
