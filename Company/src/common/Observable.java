package common;

public interface Observable {
	
	public boolean attach(IRent rent, IVehicle vehicle);

	public boolean detach(IRent rent, IVehicle vehicle);

	public void notifyObserver(IVehicle vehicle);
	
}
