package car_rental;

import common.IPerson;

public interface ICarRental {
	
	public Rent rentVehicle(IPerson person, IVehicle vehicle, String startDate, String endDate);
	
	public void returnVehicle(Rent rent);
	
}
