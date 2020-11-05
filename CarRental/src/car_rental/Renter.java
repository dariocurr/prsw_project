package car_rental;

import java.util.Objects;

import common.IRenter;

public class Renter implements IRenter {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean discounted;
	
	
	
	public Renter(String firstName, String lastName, String email, String password, boolean discounted) {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		Objects.requireNonNull(email);
		Objects.requireNonNull(firstName);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.discounted = discounted;
	}
	
	@Override
	public boolean isDiscounted() {
		return discounted;
	}

	@Override
	public void update() {
		
	}
	

}
