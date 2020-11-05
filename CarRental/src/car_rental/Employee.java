package car_rental;

public class Employee extends Renter {
	
	public Employee(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password, true);
	}
	
}
