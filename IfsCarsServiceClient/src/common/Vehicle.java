package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import common.IVehicle;

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

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public String getYear() {
		return year;
	}

	@Override
	public int getSeats() {
		return seats;
	}

	@Override
	public int getDoors() {
		return doors;
	}

	@Override
	public String getTrasmission() {
		return trasmission;
	}

	@Override
	public String getSize() {
		return size;
	}

	@Override
	public double getPricePerDay() {
		return pricePerDay;
	}

	@Override
	public List<String> getNotes() {
		return notes;
	}
	
	@Override
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public boolean isForSale() {
		return forSale;
	}

	@Override
	public void setForSale(boolean forSale) {
		this.forSale = forSale;
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
