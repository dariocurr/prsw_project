package common;

import java.util.List;
import java.util.Objects;

public class Vehicle implements IVehicle {
	
	private String name;
	private List<String> notes;
	
	public Vehicle(String name, List<String> notes) {
		super();
		Objects.requireNonNull(name);
		Objects.requireNonNull(notes);
		this.name = name;
		this.notes = notes;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public List<String> getNotes() {
		return notes;
	}
	
}
