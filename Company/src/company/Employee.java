package company;

public class Employee implements IEmployee {
	
	private String name;
	
	public Employee(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
