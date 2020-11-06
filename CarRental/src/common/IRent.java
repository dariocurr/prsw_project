package common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRent extends Serializable {

	public IRenter getRenter();
	
	public IVehicle getVehicle();
	
	public String getEndDate();
	
}
