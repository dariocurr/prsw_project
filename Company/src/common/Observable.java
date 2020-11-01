package common;

public interface Observable {
	
	public boolean attach(IRenter renter, IVehicle vehicle);

	public boolean detach(IRenter renter, IVehicle vehicle);

	public void notifyObservers(IVehicle vehicle);
	
}
