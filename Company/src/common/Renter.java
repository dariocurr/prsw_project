package common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Renter extends UnicastRemoteObject implements IRenter {
	
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
	public void update() throws RemoteException {
		
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + " " + this.email;
	}

	@Override
	public boolean isTrusted() throws RemoteException {
		return this.trusted;
	}
	

}
