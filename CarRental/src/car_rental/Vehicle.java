package car_rental;

import java.util.List;

public class Vehicle implements IVehicle {
	
	private String name;
	private List<String> notes;
	
	public String getName() {
		return name;
	}
	
	public List<String> getNotes() {
		return notes;
	}
	
}
