package company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ICarRentalObservable;
import common.IRent;
import common.IRenterObserver;
import common.IVehicle;
import common.Renter;
import gui.VehicleRentalGUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** Proxy used to connect the client with the server */
public class ClientProxy {
	
	private ICarRentalObservable carRental;
	private List<IRenterObserver> renters;
	
	/** Default constructor which initialize the @ICarRentalObservable object */
	public ClientProxy() throws MalformedURLException, RemoteException, NotBoundException {
		this.renters = ClientProxy.loadRentersFromFile("res" + File.separator + "renters_list.json");
		
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		path = path.substring(0, path.lastIndexOf(File.separator));
		String policy_path = "file:" + File.separator + File.separator + path + File.separator + "EiffelCorp" + File.separator + "src" + File.separator + "company" + File.separator + "client.policy";
		String codebase_path = "file:" + File.separator + File.separator + path + File.separator + "IfsCars" + File.separator + "bin" + File.separator;
		System.setProperty("java.security.policy", policy_path);
		System.setProperty("java.rmi.server.codebase", codebase_path);
		System.setSecurityManager(new RMISecurityManager());
		carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
	}
	
	
	/** Get the list of the available vehicles using the @ICarRentalObservable object
	 * @return list of available vehicles */
	public List<IVehicle> getAvailableVehicles() throws RemoteException {
		return this.carRental.getAvailableVehicles();
	}
	
	/** Get the list of rents of a given renter, using a method from @ICarRentalObservable
	 * @param renter the renter
	 * @return list of rents */
	public List<IRent> getRenterRentals(IRenterObserver renter) throws RemoteException {
		return this.carRental.getRenterRentals(renter);
	}
	
	/** Check via RMI if the coupon inserted is valid
	 * @param coupon code
	 * @return true if the coupon is valid
	 */
	public boolean checkCoupon(String code) {
		try {
			return this.carRental.checkCouponCode(code);
		} catch (RemoteException e) {
			return false;
		}
	}
	
	/** Rent a vehicle, using a method from @ICarRentalObservable
	 * @param renter the employee who rents the vehicle
	 * @param vehicle the vehicle rented
	 * @param startDate the date of the beginning of the rent
	 * @param endDate the date of the end of the rent
	 * @param coupon the coupon
	 * @return the rent */
	public IRent rentVehicle(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException {
		return this.carRental.rentVehicle(renter, vehicle, startDate, endDate, coupon);
	}
	
	private static List<IRenterObserver> loadRentersFromFile(String url) {
		JSONParser jsonParser = new JSONParser();
		List<IRenterObserver> rentersList = new ArrayList<>();
		try (FileReader reader = new FileReader(url)) {
            Object obj = jsonParser.parse(reader);
            JSONArray renters = (JSONArray) obj;
            for(Object renter : renters) {
            	rentersList.add(parseRentersObject( (JSONObject) renter));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return rentersList;
	}
	
	private static IRenterObserver parseRentersObject(JSONObject renterObject) {
		String firstName = (String) renterObject.get("firstName");
		String lastDay = (String) renterObject.get("lastName"); 
		String age = (String) renterObject.get("age");
		String email = (String) renterObject.get("email");
		String password = (String) renterObject.get("password");
		IRenterObserver renter = new Renter (firstName, lastDay, Integer.parseInt(age), email, password, true);
        return renter;
    }
	
	/** Get the credentials of all the renters
	 * @return map with all email and passwords */
	public Map<String, String> getCredentials(){
		Map<String,String> credentialsMap = new HashMap<>();
		for(IRenterObserver renter : this.renters)
			credentialsMap.put(renter.getEmail(), renter.getPassword());
		
		return credentialsMap;
	}
	
	/** Get the list of the renters
	 * @return the list of the renters */
	public List<IRenterObserver> getRenters() {
		return this.renters;
	}
	
	/** Return a vehicle, using a method from @ICarRentalObservable
	 * @param rent the rent
	 * @param notes a list of notes written by the renter */
	public void returnVehicle(IRent rent, List<String> notes) throws RemoteException{
		this.carRental.returnVehicle(rent, notes);
	}
	
	/** Get all the not available vehicles, using a method from @ICarRentalObservable
	 * @return map with all the vehicles and the end date of the rent */
	public Map<IVehicle, String> getNotAvailableVehicles() throws RemoteException{
		return this.carRental.getNotAvailableVehicles();
	}
	
	/** Add a rent in the waitlist, using a method from @ICarRentalObservable
	 * @param renter the renter
	 * @param vehicle the vehicle
	 * @param startDate the date of the beginning of the rent
	 * @param endDate the date of the end of the rent
	 * @param coupon the coupon
	 * @return the rent */
	public IRent attach(IRenterObserver renter, IVehicle vehicle, String startDate, String endDate, String coupon) throws RemoteException{
		return this.carRental.attach(renter, vehicle, startDate, endDate, coupon);
	}
	
	/** Remove a rent from the waitlist, using a method from @ICarRentalObservable
	 * @param rent the rent to remove
	 * @return true if the remove operation is performed correctly, false otherwise */
	public boolean detach(IRent rent) throws RemoteException{
		return this.carRental.detach(rent);
	}
	
}