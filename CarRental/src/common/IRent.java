package common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRent extends Serializable {
	
	public ARenter getRenter();
	
	public IVehicle getVehicle();
	
	public String getStartDate();

	public String getEndDate();

	public int getDaysNumber();
	
	public double getPrice();
	
}
