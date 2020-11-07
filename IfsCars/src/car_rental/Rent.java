package car_rental;

import java.rmi.RemoteException;
import java.util.Objects;

import common.IRent;
import common.IRenterObserver;
import common.IVehicle;

public class Rent implements IRent {

	private IRenterObserver renter;
	private IVehicle vehicle;
	private String startDate;
	private String endDate;
	private int daysNumber;
	private double price;

	public Rent(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, double discount) throws RemoteException {
		Objects.requireNonNull(vehicle);
		Objects.requireNonNull(renter);
		Objects.requireNonNull(startDate);
		Objects.requireNonNull(endDate);
		this.vehicle = vehicle;
		this.renter = renter;
		this.startDate = startDate;
		this.endDate = endDate;
		this.daysNumber = Integer.valueOf(endDate.substring(0, 2)) - Integer.valueOf(startDate.substring(0, 2));
		this.price = (this.vehicle.getPricePerDay() * this.daysNumber) * (1 - discount);
	}

	@Override
	public IRenterObserver getRenter() {
		return renter;
	}
	
	@Override
	public IVehicle getVehicle() {
		return vehicle;
	}

	@Override
	public String getStartDate() {
		return startDate;
	}

	@Override
	public String getEndDate() {
		return endDate;
	}

	@Override
	public int getDaysNumber() {
		return daysNumber;
	}
	
	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Rent otherRent = (Rent) otherObject;
		return this.vehicle.equals(otherRent.vehicle)
				&& this.renter.equals(otherRent.renter)
				&& this.startDate.equalsIgnoreCase(otherRent.startDate)
				&& this.endDate.equalsIgnoreCase(otherRent.endDate);
	}

}