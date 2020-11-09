package client;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.ICarSeller;
import common.IVehicle;
import service.CarSeller;
import service.CarSellerServiceLocator;

public class IfsCarsServiceClient {
	private CarSeller service;
	
	public IfsCarsServiceClient() throws ServiceException {
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		path = path.substring(0, path.lastIndexOf(File.separator));
		String policy_path = "file:" + File.separator + File.separator + path + File.separator + "EiffelCorp" + File.separator + "src" + File.separator + "company" + File.separator + "sec.policy";
		String codebase_path = "file:" + File.separator + File.separator + path + File.separator + "IfsCars" + File.separator + "bin" + File.separator;
		System.setProperty("java.security.policy", policy_path);
		System.setProperty("java.rmi.server.codebase", codebase_path);
		System.setSecurityManager(new RMISecurityManager());
		this.service = (CarSeller) new CarSellerServiceLocator().getCarSeller();
	}
	
	public List<IVehicle> getVehicles() throws RemoteException {
		List<IVehicle> vehicles = new ArrayList<>();
		System.out.println(service.getAvailableVehiclesForSale());
		return vehicles;
	}

}
