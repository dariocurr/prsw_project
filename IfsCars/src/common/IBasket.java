package common;

import java.util.List;

/** Add, remove and get the informations about the basket */
public interface IBasket {

	/** Get the list of the vehicles in the basket
	 * @return list of the vehicles inside the basket */
	public List<IVehicle> getVehiclesInBasket();
	
	/** Add a vehicle to the basket
	 * @param vehicle the vehicle that has to be added in the basket
	 * @return true if the add operation is done without errors, false otherwise */
	public boolean addVehicleToBasket(IVehicle vehicle);
	
	/** Remove a vehicle from the basket
	 * @param vehicle the vehicle that has to be removed from the basket
	 * @return true if the remove operation is done without errors, false otherwise */
	public boolean removeVehicleFromBasket(IVehicle vehicle);
	
	/** Get the current total price of the basket
	 * @return the total price */
	public double getTotalPrice();
	
	/** Get the list of the vehicles' models in the basket
	 * @return the list of the models */
	public List<String> getVehicleModel();
	
	/** Empty the basket */
	public void empty();
	
}
