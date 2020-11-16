package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import common.IVehicle;

/** Class which implements the @IVehicle interface */
public class Vehicle implements IVehicle {
	
	private String model;
	private String year;
	private int seats;
	private int doors;
	private String trasmission;
	private String size;
	private double pricePerDay;
	private double price;
	private List<String> notes;
	private String fileName;
	private boolean forSale;
	
	/** Main constructor which initialize a vehicle 
	 * @param model the vehicle's model 
	 * @param year the vehicle's year
	 * @param seats the vehicle's number of seats
	 * @param doors the vehicle's number of doors
	 * @param trasmission the vehicle's transmission
	 * @param size the vehicle's size
	 * @param pricePerDay the vehicle's price per day
	 * @param price the vehicle's price
	 * @param fileName the vehicle's file name */
	public Vehicle(String model, String year, int seats, int doors, String trasmission, String size, double pricePerDay, double price, String fileName) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(year);
		Objects.requireNonNull(trasmission);
		Objects.requireNonNull(size);
		Objects.requireNonNull(fileName);
		this.model = model;
		this.year = year;
		this.seats = seats;
		this.doors = doors;
		this.trasmission = trasmission;
		this.size = size;
		this.pricePerDay = pricePerDay;
		this.price = price;
		this.notes = new ArrayList<String>();
		this.fileName = fileName;
		this.forSale = false;
	}

	/** {@inheritDoc} */
	@Override
	public String getModel() {
		return model;
	}

	/** {@inheritDoc} */
	@Override
	public String getYear() {
		return year;
	}

	/** {@inheritDoc} */
	@Override
	public int getSeats() {
		return seats;
	}

	/** {@inheritDoc} */
	@Override
	public int getDoors() {
		return doors;
	}

	/** {@inheritDoc} */
	@Override
	public String getTrasmission() {
		return trasmission;
	}

	/** {@inheritDoc} */
	@Override
	public String getSize() {
		return size;
	}

	/** {@inheritDoc} */
	@Override
	public double getPricePerDay() {
		return pricePerDay;
	}

	/** {@inheritDoc} */
	@Override
	public List<String> getNotes() {
		return notes;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getFileName() {
		return this.fileName;
	}

	/** {@inheritDoc} */
	@Override
	public double getPrice() {
		return price;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isForSale() {
		return forSale;
	}

	/** {@inheritDoc} */
	@Override
	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}
	
	@Override
	public String toString() {
		return this.model+" ["+this.year+"]";
	}

	@Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        Vehicle otherVehicle = (Vehicle) otherObject;
        return this.model.equalsIgnoreCase(otherVehicle.model) && this.year.equalsIgnoreCase(otherVehicle.year);
    }
	
	@Override
	public int hashCode() {
		return this.model.hashCode() + this.year.hashCode();
	}
	
}
