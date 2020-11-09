package client;

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
		this.service = (CarSeller) new CarSellerServiceLocator().getCarSeller();
	}
	
	public List<IVehicle> getVehicles() throws RemoteException {
		List<IVehicle> vehicles = new ArrayList<>();
		System.out.println(service.getAvailableVehiclesForSale());
		return vehicles;
	}

}