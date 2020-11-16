package client;

import java.util.ArrayList;
import java.util.List;

import common.IBasket;
import common.IVehicle;

/** Class which implements @IBasket interface */
public class Basket implements IBasket {

	private List<IVehicle> vehiclesBasket;
	
	/* Default constructor used to initialize the basket */
	public Basket() {
		this.vehiclesBasket = new ArrayList<IVehicle>();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<IVehicle> getVehiclesInBasket() {
		return this.vehiclesBasket;
	}

	/** {@inheritDoc} */
	@Override
	public boolean addVehicleToBasket(IVehicle vehicle) {
		return this.vehiclesBasket.add(vehicle);
	}

	/** {@inheritDoc} */
	@Override
	public boolean removeVehicleFromBasket(IVehicle vehicle) {
		return this.vehiclesBasket.remove(vehicle);
	}
	
	/** {@inheritDoc} */
	@Override
	public double getTotalPrice() {
		double totalPrice = 0.0;
		for(IVehicle vehicle : vehiclesBasket) {
			totalPrice += vehicle.getPrice();
		}
		return totalPrice;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<String> getVehicleModel() {
		List<String> models = new ArrayList<String>();
		for(IVehicle vehicle : vehiclesBasket) {
			models.add(vehicle.getModel());
		}
		return models;
	}
	
	/** {@inheritDoc} */
	@Override
	public void empty() {
		vehiclesBasket.removeAll(vehiclesBasket);
	}
	
}
