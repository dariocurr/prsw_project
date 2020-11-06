package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class ARenter extends APerson implements IObserver {
	
	private String email;
	private String password;
	private List<IRent> rentals;
	private boolean trusted;
	
	public ARenter(String firstName, String lastName, int age, String email, String password, boolean trusted) {
		super(firstName, lastName, age);
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
		this.email = email;
		this.password = password;
		this.rentals = new ArrayList<>();
		this.trusted = trusted;
	}
	
	public boolean isTrusted() {
		return this.trusted;
	}
	
	@Override
	public abstract void update();
	
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
        ARenter otherRenter = (ARenter) otherObject;
        return super.equals(otherRenter)
        		&& this.email.equalsIgnoreCase(otherRenter.email);
	}

	@Override
	public int hashCode() {
		return this.firstName.hashCode() + this.lastName.hashCode() + this.email.hashCode();
	}
	
}
