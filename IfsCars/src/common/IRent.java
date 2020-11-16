package common;

import java.io.Serializable;

/** Interface to get all the informations abount the rent */
public interface IRent extends Serializable {
	
	/** Get the renter of the rent
	 * @return the renter */
	public IRenterObserver getRenter();
	
	/** Get the vehicle in the rent
	 * @return the vehicle */
	public IVehicle getVehicle();
	
	/** Get the start date of the rent
	 * @return the start date */
	public String getStartDate();

	/** Get the end date of the rent
	 * @return the end date */
	public String getEndDate();

	/** Get the number of the days in the rent
	 * @return the number of the days in the rent */
	public int getDaysNumber();
	
	/** Get the price of the rent
	 * @return the price of the rent*/
	public double getPrice();
	
}
