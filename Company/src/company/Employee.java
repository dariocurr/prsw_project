package company;

import common.Renter;

public class Employee extends Renter {

	private String name;
	
	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
