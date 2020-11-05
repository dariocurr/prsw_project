package car_rental;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import common.IVehicle;

public class Vehicle extends UnicastRemoteObject implements IVehicle {
	
	private String model;
	private String year;
	private int seats;
	private int doors;
	private String trasmission;
	private String size;
	private double pricePerDay;
	private List<String> notes;
	
	public Vehicle() throws RemoteException {}
	
	public Vehicle(String model, String year, int seats, int doors, String trasmission, String size, double pricePerDay) throws RemoteException {
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
	public String getModel() throws RemoteException {
		return model;
	}
	
	@Override
	public List<String> getNotes() throws RemoteException {
		return notes;
	}
	
	@Override
	public double getPricePerDay() throws RemoteException {
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
	
}
