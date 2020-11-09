package client;

import java.util.ArrayList;
import java.util.List;

import common.IBasket;
import common.IVehicle;

public class Basket implements IBasket {

	private List<IVehicle> vehiclesBasket;
	
	public Basket() {
		this.vehiclesBasket = new ArrayList<IVehicle>();
	}
	
	@Override
	public List<IVehicle> getVehiclesInBasket() {
		return this.vehiclesBasket;
	}

	@Override
	public boolean addVehicleToBasket(IVehicle vehicle) {
		return this.vehiclesBasket.add(vehicle);
	}

	@Override
	public boolean removeVehicleFromBasket(IVehicle vehicle) {
		return this.vehiclesBasket.remove(vehicle);
	}
	
	@Override
	public double getTotalPrice() {
		double totalPrice = 0.0;
		for(IVehicle vehicle : vehiclesBasket) {
			totalPrice += vehicle.getPrice();
		}
		return totalPrice;
	}
	
	@Override
	public List<String> getVehicleModel() {
		List<String> models = new ArrayList<String>();
		for(IVehicle vehicle : vehiclesBasket) {
			models.add(vehicle.getModel());
		}
		return models;
	}
	
}
