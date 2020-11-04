package common;

import java.util.Objects;

public class Rent implements IRent {
	
	private IVehicle vehicle;
	private IRenter renter;
	private String startDate;
	private String endDate;
	
	public Rent(IRenter renter, IVehicle vehicle, String startDate, String endDate) {
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
	
	@Override
	public IRenter getRenter() {
		return this.renter;
	}
	
}
