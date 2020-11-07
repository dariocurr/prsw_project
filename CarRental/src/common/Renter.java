package common;

import java.util.Objects;

public class Renter extends APerson implements IRenterObserver {
	
	private String email;
	private String password;
	private boolean trusted;
	
	public Renter(String firstName, String lastName, int age, String email, String password, boolean trusted) {
		super(firstName, lastName, age);
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
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
        return super.equals(otherRenter)
        		&& this.email.equalsIgnoreCase(otherRenter.email);
	}

	@Override
	public int hashCode() {
		return this.firstName.hashCode() + this.lastName.hashCode() + this.email.hashCode();
	}
	
	@Override
	public void update() {
		
	}
	
}
