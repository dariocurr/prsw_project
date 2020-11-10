package common;

import java.util.List;

public interface IBasket {

	public List<IVehicle> getVehiclesInBasket();
	
	public boolean addVehicleToBasket(IVehicle vehicle);
	
	public boolean removeVehicleFromBasket(IVehicle vehicle);
	
}
