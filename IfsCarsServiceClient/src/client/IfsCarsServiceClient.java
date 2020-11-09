package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

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
		// è normale sia vuota, perche nessun veicolo è pronto per la vendita
		// per test, ti conviene commentare riga 54 e 56 in CarSeller
		System.out.println(service.getAvailableForSaleVehicles());
		return vehicles;
	}

}
