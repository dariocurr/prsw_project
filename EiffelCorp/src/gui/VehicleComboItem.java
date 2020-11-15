package gui;

import common.IVehicle;

/** Class for the item in the {@ReturnPanel} and {@RentPanel} combobsox */
public class VehicleComboItem {
	private String text;
    private IVehicle vehicle;
    
    /** Main constructor, it initializes the vehicle and the text of the item in the combobox
     * @param v the vehicle to place in the combobox */
    public VehicleComboItem(IVehicle v){
        this.vehicle = v;
        this.text = v.getModel()+" "+v.getPricePerDay()+"€";
    }
    
    /** Get the current vehicle.
     * @return the current vehicle in the combobox */
    public IVehicle getVehicle() {
		return this.vehicle;
	}
    
    /** Show the text of a vehicle.
     * @return the text of a vehicle (model and price per day) */
    @Override
    public String toString() {
    	return this.text;
    }

}
