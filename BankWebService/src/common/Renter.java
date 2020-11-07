package common;

import java.util.Objects;

public class Renter implements IRenterObserver {
	
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String password;
	private boolean trusted;
	
	public Renter() {}
	
	public Renter(String firstName, String lastName, int age, String email, String password, boolean trusted) {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.password = password;
		this.trusted = trusted;
	}
	
	@Override
	public boolean isTrusted() {
		return this.trusted;
	}
	
	@Override
	public String toString() {
		return this.firstName + "\t" + this.lastName + "\t" + this.email;
	}
	
	@Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        Renter otherRenter = (Renter) otherObject;
        return this.firstName.equalsIgnoreCase(otherRenter.firstName) 
        		&& this.lastName.equals(otherRenter.lastName)
        		&& this.age == otherRenter.age
        		&& this.email.equalsIgnoreCase(otherRenter.email);
	}

	@Override
	public int hashCode() {
		return this.firstName.hashCode() + this.lastName.hashCode() + this.email.hashCode();
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	
	
}
