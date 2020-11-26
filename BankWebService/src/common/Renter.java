package common;

import java.util.Objects;

/** Class which implements @IRenterObserver interface */
public class Renter implements IRenterObserver {
	
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String password;
	private boolean trusted;
	
	/** Default constructor, empty*/
	public Renter() {}
	
	/** Main constructor which initialize the renter's informations
	 * @param firstName the renter's first name
	 * @param lastName the renter's last name
	 * @param age the renter's age
	 * @param email the renter's email
	 * @param password the renter's password
	 * @param trusted if the renter is trusted */
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
	
	/** {@inheritDoc} */
	@Override
	public boolean isTrusted() {
		return this.trusted;
	}
	
	/** {@inheritDoc} */
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
	
	/** {@inheritDoc} */
	@Override
	public boolean update() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public String getEmail() {
		return this.email;
	}

	/** {@inheritDoc} */
	@Override
	public String getPassword() {
		return this.password;
	}
	
}
