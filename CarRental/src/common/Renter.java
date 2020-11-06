package common;

import java.util.List;

public class Renter extends ARenter {
	
	private String email;
	private String password;
	private List<IRent> rentals;
	private boolean trusted;
	
	public Renter(String firstName, String lastName, int age, String email, String password, boolean trusted) {
		super(firstName, lastName, age, email, password, trusted);
	}
	
	@Override
	public void update() {
		
	}
	
}
