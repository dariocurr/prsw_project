package common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVehicle extends Serializable {

	public String getModel();
	public List<String> getNotes();
	public double getPricePerDay();
	
}
