package common;

import java.io.Serializable;

public interface IRent extends Serializable {

	public IRenter getRenter();
	
	public IVehicle getVehicle();
	
}
