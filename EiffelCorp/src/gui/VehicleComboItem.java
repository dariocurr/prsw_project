package gui;

import common.IVehicle;

public class VehicleComboItem {
	private String text;
    private IVehicle vehicle;
    
    public VehicleComboItem(IVehicle v){
        this.vehicle = v;
        this.text = v.getModel()+" "+v.getPricePerDay()+"€";
    }
    
    public IVehicle getVehicle() {
		return this.vehicle;
	}
    
    @Override
    public String toString() {
    	return this.text;
    }

}
