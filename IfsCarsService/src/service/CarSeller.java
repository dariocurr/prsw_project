package service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.rpc.ServiceException;

import bank.BankServiceLocator;
import common.IBank;
import common.IBasket;
import common.ICarRentalObservable;
import common.IVehicle;

public class CarSeller implements ICarSeller {

	private ICarRentalObservable carRental;
	private IBank bank;
	
	public CarSeller() throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		path = path.substring(0, path.lastIndexOf(File.separator));
		String policy_path = "file:" + File.separator + File.separator + path + File.separator + "EiffelCorp" + File.separator + "src" + File.separator + "company" + File.separator + "sec.policy";
		String codebase_path = "file:" + File.separator + File.separator + path + File.separator + "IfsCars" + File.separator + "bin" + File.separator;
		System.setProperty("java.security.policy", policy_path);
		System.setProperty("java.rmi.server.codebase", codebase_path);
		System.setSecurityManager(new RMISecurityManager());
		carRental = (ICarRentalObservable) Naming.lookup("CarRentalService");
		this.bank = (IBank) new BankServiceLocator().getBank();
	}
	
	@Override
	public String getAvailableVehiclesForSale() throws RemoteException {
		// JSON TO RETURN
		//List<IVehicle> availableVehiclesForSale = this.carRental.getAvailableVehicles().stream().filter(IVehicle::isForSale).collect(Collectors.toList());
		//return (Vehicle[]) availableVehiclesForSale.toArray(new IVehicle[availableVehiclesForSale.size()]);
		return null;
	}
	
	@Override
	public boolean sellVehicle(String basket, String accountNumber, double amount, String currency) throws RemoteException {
		Objects.requireNonNull(basket);
		Objects.requireNonNull(accountNumber);
		Objects.requireNonNull(currency);
		boolean isPaymentDone = this.bank.makePayment(accountNumber, amount, currency);
		// RECONSTRUCT VEHICLES
		/*
		if (isPaymentDone) {
			this.carRental.getAvailableVehicles().removeAll(basket.getVehiclesInBasket());
		}*/
		return isPaymentDone;
	}
	
}
