package company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import common.ICarRentalObservable;
import common.IRent;
import common.IRenterObserver;
import common.IVehicle;
import gui.VehicleRentalGUI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.formdev.flatlaf.json.ParseException;

public class ClientProxy {
	ICarRentalObservable carRental;
	
	
	
	public ClientProxy() throws MalformedURLException, RemoteException, NotBoundException {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		System.setProperty("java.security.policy","src\\company\\sec.policy");
		System.setSecurityManager(new RMISecurityManager());
		System.setProperty("java.rmi.server.codebase", "../CarRental/bin/");
		carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
		
		//System.setProperty("java.rmi.server.codebase", "../../../CarRental/bin/");
		//System.setProperty("java.security.policy","file:///home/dario/Documenti/Rest/progetto/prsw/Company/src/company/sec.policy");
		//System.setProperty("java.rmi.server.codebase", "file:///home/dario/Documenti/Rest/progetto/prsw/CarRental/bin/");
		
	}
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		new ClientProxy();
		VehicleRentalGUI gui = new VehicleRentalGUI();
		
		/*try {
			
			IRenterObserver renter = new Renter("cugino", "antonio", 22, "fafas@ga", "123", true);
			System.out.println("DISPONIBILI");
			for (IVehicle vehicle : carRental.getAvailableVehicles()) {
				System.out.println(vehicle.getModel());
			}
			this.carRental.rentVehicle(renter, carRental.getAvailableVehicles().get(0), "26/01/97" , "28/01/97", "EMP001");
			this.carRental.rentVehicle(renter, carRental.getAvailableVehicles().get(1), "29/01/97" , "30/01/97", "EMP002");
			System.out.println("\nAFFITTATE");
			for (IRent rent : carRental.getRenterRentals(renter)) {
				System.out.println(rent.getVehicle());
			}
			System.out.println("\nDISPONIBILI");
			for (IVehicle vehicle : carRental.getAvailableVehicles()) {
				System.out.println(vehicle.getModel());
			}
			System.out.println("\nLISTA ATTESA");
			for (IVehicle vehicle : carRental.getNotAvailableVehicles().keySet()) {
				System.out.println(vehicle.getModel() + " " + carRental.getNotAvailableVehicles().get(vehicle));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}*/
	
	/*
	@Override
	public boolean createRenter(String firstName, String lastName, String email, String password, String discountCode) {
		IRenter renter;
		if (discountCode == "EMP001") {
			renter = new Employee(firstName, lastName, email, password);
		} else {
			renter = new Renter(firstName, lastName, email, password, false);
		}
		this.renters.stream().forEach(v -> System.out.println(v));
		return this.renters.add(renter);*/
	} 
	
	public List<IVehicle> getAvailableVehicles() throws RemoteException {
		return this.carRental.getAvailableVehicles();
	}
	
	public List<IRent> getRenterRentals(IRenterObserver renter) throws RemoteException{
		return this.carRental.getRenterRentals(renter);
	}
	
	private List<IRenterObserver> loadRentersFromFile(String url) throws RemoteException{
		JSONParser jsonParser = new JSONParser();
		
		List<IRenterObserver> rentersList = new ArrayList<>();
		
		try (FileReader reader = new FileReader(url))
        {
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
        } catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		return rentersList;
	}
	
	private static IRenterObserver parseRentersObject(JSONObject renter) throws RemoteException {
		
		String firstName = (String) renter.get("firstName");
		String lastDay = (String) renter.get("lastName"); 
		String age = (String) renter.get("age");
		String email = (String) renter.get("email");
		String password = (String) renter.get("password");
	
		IRenterObserver renter_obj = new Renter (firstName, lastDay, Integer.parseInt(age), email, password, true);
         
        return renter_obj;
    }
	
}