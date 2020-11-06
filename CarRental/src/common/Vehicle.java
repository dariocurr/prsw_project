package common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehicle implements IVehicle {
	
	private String model;
	private String year;
	private int seats;
	private int doors;
	private String trasmission;
	private String size;
	private double pricePerDay;
	private List<String> notes;
	
	public Vehicle(String model, String year, int seats, int doors, String trasmission, String size, double pricePerDay) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(year);
		Objects.requireNonNull(trasmission);
		Objects.requireNonNull(size);
		this.model = model;
		this.year = year;
		this.seats = seats;
		this.doors = doors;
		this.trasmission = trasmission;
		this.size = size;
		this.pricePerDay = pricePerDay;
		this.notes = new ArrayList<String>();
	}

	@Override
	public String getModel() {
		return model;
	}
	
	@Override
	public List<String> getNotes() {
		return notes;
	}
	
	@Override
	public double getPricePerDay() {
		return this.pricePerDay;
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

	@Override
	public String toString() {
		return this.model + "\t" + this.year + "\tdoors: " + this.doors + "\tseats: " + this.seats + "\ttrasmission: " + this.trasmission + "\tprice per day: " + this.pricePerDay;  
	}
	
}
