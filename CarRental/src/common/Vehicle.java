package common;

import java.util.List;
import java.util.Objects;

public class Vehicle implements IVehicle {
	
	private String name;
	private String year;
	private double pricePerDay;
	private List<String> notes;
	
	public Vehicle(String name, String year, double pricePerDay, List<String> notes) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(notes);
		this.name = name;
		this.notes = notes;
		this.year = year;
		this.pricePerDay = pricePerDay;
	}

	@Override
	public String getName() {
		return name;
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
        return this.name.equalsIgnoreCase(otherVehicle.name) && this.year.equalsIgnoreCase(otherVehicle.year);
    }
	
}
