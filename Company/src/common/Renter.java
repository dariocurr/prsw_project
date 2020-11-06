package common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Renter implements IRenter {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<IRent> rentals;
	private boolean trusted;
	
	public Renter() throws RemoteException {}
	
	public Renter(String firstName, String lastName, String email, String password, boolean trusted) throws RemoteException {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		Objects.requireNonNull(email);
		Objects.requireNonNull(firstName);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.rentals = new ArrayList<>();
		this.trusted = trusted;
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public String toString() {
		return this.firstName + "\t" + this.lastName + "\t" + this.email;
	}

	@Override
	public boolean isTrusted() {
		return this.trusted;
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
        		&& this.email.equalsIgnoreCase(otherRenter.email);
	}

	@Override
	public int hashCode() {
		return this.firstName.hashCode() + this.lastName.hashCode() + this.email.hashCode();
	}
	
	
	
}
