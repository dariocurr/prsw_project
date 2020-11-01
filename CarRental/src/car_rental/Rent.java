package car_rental;

import java.util.Objects;

import common.IRenter;
import common.IVehicle;

public class Rent implements IRent {
	
	private IVehicle vehicle;
	private IRenter renter;
	private String startDate;
	private String endDate;
	
	public Rent(IRenter renter, IVehicle vehicle, String startDate, String endDate) {
		super();
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(renter);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		this.vehicle = vehicle;
		this.renter = renter;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public IVehicle getVehicle() {
		return this.vehicle;
	}
	
}
