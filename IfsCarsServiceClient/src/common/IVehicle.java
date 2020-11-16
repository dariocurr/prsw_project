package common;

import java.io.Serializable;
import java.util.List;

/** Interface to manage all the informations about a vehicle */
public interface IVehicle extends Serializable {

	/** Get the vehicle's model
	 * @return the vehicle's model */
	public String getModel();

	/** Get the vehicle's year
	 * @return the vehicle's year */
	public String getYear();

	/** Get the vehicle's number of seats
	 * @return the vehicle's number of seats */
	public int getSeats();

	/** Get the vehicle's number of doors
	 * @return the vehicle's number of doors */
	public int getDoors();

	/** Get the vehicle's transmission
	 * @return the vehicle's transmission */
	public String getTrasmission();

	/** Get the vehicle's size
	 * @return the vehicle's size */
	public String getSize();

	/** Get the vehicle's price per day
	 * @return the vehicle's price per day */
	public double getPricePerDay();
	
	/** Get the vehicle's total price
	 * @return the vehicle's total price */
	public double getPrice();

	/** Get the vehicle's notes
	 * @return the vehicle's notes */
	public List<String> getNotes();
	
	/** Get the vehicle's file name
	 * @return the vehicle's file name */
	public String getFileName();
	
	/** Verify if the vehicle is for sale
	 * @return true if the vehicle is for sale, false otherwise */
	public boolean isForSale();

	/** Set the attribute forSale of the vehicle
	 * @param forSale true if the vehicle is for sale, false otherwise */
	public void setForSale(boolean forSale);
	
}
