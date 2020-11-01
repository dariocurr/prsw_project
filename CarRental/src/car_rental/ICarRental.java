package car_rental;

import common.IRenter;
import common.IVehicle;
import common.Rent;

public interface ICarRental {
	
	public Rent rentVehicle(IRenter renter, IVehicle vehicle, String startDate, String endDate);
	
	public void returnVehicle(Rent rent);
	
}
