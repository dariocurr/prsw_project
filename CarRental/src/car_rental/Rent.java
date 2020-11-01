package car_rental;

import common.IPerson;

public class Rent implements IRent {
	
	private IVehicle vehicle;
	private IPerson person;
	
	public Rent(IVehicle vehicle, IPerson person) {
		super();
		this.vehicle = vehicle;
		this.person = person;
	}	
	
}
