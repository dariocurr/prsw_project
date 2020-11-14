package common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVehicle extends Serializable {

	public String getModel();

	public String getYear();

	public int getSeats();

	public int getDoors();

	public String getTrasmission();

	public String getSize();

	public double getPricePerDay();
	
	public double getPrice();

	public List<String> getNotes();
	
	public String getFileName();
	
	public boolean isForSale();

	public void setForSale(boolean forSale);

	
}
